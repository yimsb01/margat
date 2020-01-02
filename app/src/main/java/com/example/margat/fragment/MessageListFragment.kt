package com.example.margat.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.margat.R
import com.example.margat.activity.MainActivity
import com.example.margat.adapter.MyMessageRecyclerViewAdapter
import com.example.margat.controller.MessageController
import com.example.margat.model.MessageItem
import kotlinx.android.synthetic.main.fragment_message_list.*

class MessageListFragment: Fragment {

    private var mActivity: MainActivity

    private var mNum: Int = 0
    private var mList: ArrayList<MessageItem>

    private lateinit var mMessageListRecycler: RecyclerView
    private lateinit var mMessageListRecyclerViewAdapter: MyMessageRecyclerViewAdapter

    private lateinit var mMessageController: MessageController


    constructor(num: Int, mList: ArrayList<MessageItem>, mActivity: MainActivity) {
        this.mList = mList
        this.mActivity = mActivity

        var args = Bundle()
        args.putInt("num", num)
        arguments = args

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNum = if (arguments != null) arguments!!.getInt("num") else 1

        mMessageListRecyclerViewAdapter = MyMessageRecyclerViewAdapter(mList, mActivity)
        mMessageController = MessageController(mActivity, mList, mMessageListRecyclerViewAdapter)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mMessageController.loadMessageList()

        mMessageListRecycler = messageListRecycler
        mMessageListRecycler.adapter = mMessageListRecyclerViewAdapter
        mMessageListRecycler.layoutManager = LinearLayoutManager(mActivity)
    }

}