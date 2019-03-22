package com.example.kotlinnativebooking

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.kotlinnativebooking.api.client.BookingApiClientManager
import com.example.kotlinnativebooking.model.BookableData
import com.example.kotlinnativebooking.view.adapter.BookablesViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val compositeSubscription = CompositeSubscription()
    private val listItemBookables = ArrayList<BookableData>()
    private val mOnSearchButtonClickedLisner = View.OnClickListener {
        Log.d("test", "onClick")

        val txtDtFrom = findViewById(R.id.txtDtFrom) as TextView
        val txtDtTo = findViewById(R.id.txtDtTo) as TextView

        val dtFrom = txtDtFrom.text.toString()
        val dtTo = txtDtTo.text.toString()

        val df = SimpleDateFormat("yyyy-MM-dd")
        df.setLenient(false)

        val dtFromParsable= df?.let {
            try {
                it.parse(dtFrom)
                true
            } catch (e: ParseException){
                false
            }
        }

        val dtToParsable= df?.let {
            try {
                it.parse(dtTo)
                true
            } catch (e: ParseException){
                false
            }
        }

        if(dtFromParsable && dtToParsable) {

            compositeSubscription.clear()
            compositeSubscription.add(
                BookingApiClientManager.bookingApiClient.getBookables(dtFrom, dtTo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        Log.d("test", "response=$it")
                        //binding.textViewResponse.text = Gson().toJson(it)

                        listItemBookables.clear()
                        listItemBookables.addAll(it)

                        val listViewBookables = findViewById(R.id.listViewBookables) as RecyclerView
                        listViewBookables.layoutManager = LinearLayoutManager(this)
                        listViewBookables.adapter = BookablesViewAdapter(this, listItemBookables)

                    }
                    .doOnError {
                    }
                    .doOnCompleted {
                    }
                    .subscribe())

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        val txtDtFrom = findViewById(R.id.txtDtFrom) as TextView
//        val txtDtTo = findViewById(R.id.txtDtTo) as TextView
        val btnSearch = findViewById(R.id.btnSearch) as Button

        // This code has Warning.
//        btnSearch.setOnClickListener({
//                view -> Log.d("test", "onClick")
//        })

//        // This code provides SAM Convert.
//        btnSearch.setOnClickListener {
//                Log.d("test", "onClick")
//        }

//        txtDtFrom.addTextChangedListener(object: TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                val content = s?.toString()
////                s?. = if (content.length >= 6) null else "Minimum length = 6"
//            }
//            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) { }
//            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) { }
//        })

        btnSearch.setOnClickListener(mOnSearchButtonClickedLisner)

//        val hotel = HotelData()
//        hotel.id = 101
//        hotel.name = "yourstay"
//        hotel.charge = 8000
//        hotel.option1 = "option1"
//        hotel.option2 = "option2option2"
//        hotel.option3 = "option3option3"
//
//        val bookable = BookableData()
//        bookable.id = 1
//        bookable.roomCount = 5
//        bookable.dtFrom = Date()
//        bookable.dtTo = Date()
//        bookable.hotel = hotel
//
//        listItemBookables.add(bookable)
//        listItemBookables.add(bookable)
//        listItemBookables.add(bookable)
//        listItemBookables.add(bookable)

    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }
}
