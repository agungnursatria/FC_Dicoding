package com.anb.fc_dicoding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val item = intent.getParcelableExtra<Item>("club")

        DetailActivityUI(item).setContentView(this)
    }

    class DetailActivityUI(private var item: Item) : AnkoComponent<DetailActivity>{
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)

                imageView {
                    id = R.id.image
                    item.image?.let { Picasso.get().load(it).fit().into(this) }
                    padding = dip(10)
                    this@verticalLayout.gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(dip(80), dip(80))

                textView{
                    id = R.id.name
                    text = item.name
                    textSize = sp(10).toFloat()
                    gravity = Gravity.CENTER_HORIZONTAL
                    padding = dip(10)
                }

                textView{
                    id = R.id.desc
                    text = item.desc
                    gravity = Gravity.CENTER_HORIZONTAL
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    padding = dip(10)
                }

            }
        }
    }
}