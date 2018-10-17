package com.anb.fc_dicoding

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*

class RecyclerViewAdapter(private val items: List<Item>, private val listener: (Item) -> Unit)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(RecyclerViewItemUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        var image : ImageView = itemView.findViewById(R.id.image)
        var name : TextView = itemView.findViewById(R.id.name)

        fun bindItem(item: Item, listener: (Item) -> Unit) {
            name.text = item.name
            item.image?.let { Picasso.get().load(it).fit().into(image) }
            containerView.setOnClickListener {
                listener(item)
            }
        }
    }

    class RecyclerViewItemUI : AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout  {
                orientation = LinearLayout.HORIZONTAL
                lparams(matchParent, wrapContent)
                padding = dip(16)

                imageView{
                    id = R.id.image
                }.lparams(dip(50), dip(50))

                textView {
                    id = R.id.name
                }.lparams(matchParent, wrapContent){
                    margin = dip(10)
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
        }
    }
}