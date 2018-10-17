package com.anb.fc_dicoding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        initData()
        val rv = find<RecyclerView>(R.id.club_list)
        rv.adapter = RecyclerViewAdapter(items){
            startActivity<DetailActivity>("club" to it)
            toast("${it.name}")
        }
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_desc)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i],
                    image.getResourceId(i, 0),
                    desc[i]
            ))
        }

        //Recycle the typed array
        image.recycle()
    }

    class MainActivityUI : AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout{
                recyclerView {
                    id = R.id.club_list
                    layoutManager = LinearLayoutManager(context)
                }.lparams(matchParent, matchParent)
            }
        }

    }
}
