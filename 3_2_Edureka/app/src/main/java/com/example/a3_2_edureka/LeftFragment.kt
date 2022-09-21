package com.example.a3_2_edureka

import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "color"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeftFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeftFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<DataItem>
    private lateinit var communicator: Communicator

    lateinit var button :Array<String>
    lateinit var color: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newArrayList = arrayListOf<DataItem>()
        button = arrayOf(
            "Red",
            "Green",
            "Blue",
            "Grey",
            "Black"
        )

        color = arrayOf(
            R.color.red,
            R.color.green,
            R.color.blue,
            R.color.grey,
            R.color.black
        )

        for(i in button.indices){
            val dataa = DataItem(button[i],color[i])
            newArrayList.add(dataa)
        }

        adapter = MyAdapter(newArrayList)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerViewButtons)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=adapter

//        val view = inflater.inflate(R.layout.fragment_left,container,false)
        communicator = activity as Communicator

        adapter.setOnItemClickListener(object:MyAdapter.onItemCLickListener{
            override fun onItemClick(position: Int) {
                communicator.passDataCom(newArrayList[position].color)
//                Toast.makeText(context, "Clicked on ${newArrayList[position].button}", Toast.LENGTH_SHORT).show()
                println("Clicked on ${newArrayList[position].button}")
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeftFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeftFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}