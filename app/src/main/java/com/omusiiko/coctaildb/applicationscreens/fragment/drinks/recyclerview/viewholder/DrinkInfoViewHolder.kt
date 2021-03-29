package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.viewholder

import android.view.View
import android.view.ViewGroup
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.recyclerview.BaseViewHolder
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkInfoItem
import com.omusiiko.coctaildb.common.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_drink_info.view.*

class DrinkInfoViewHolder(
    view: View
) : BaseViewHolder<DrinkInfoItem>(view) {

    private val drinkNameTextView = view.textView_nameOfDrink_drinkHolder
    private val drinkPhotoImageView = view.imageView_drinkPicture_drinkHolder

    override fun bind(item: DrinkInfoItem) {
        setViews(item)
    }

    private fun setViews(item: DrinkInfoItem) {
        drinkNameTextView.text = item.name
        setImageView(item)
    }

    private fun setImageView(item: DrinkInfoItem) {
        if (item.photo.isNotEmpty()) {
            Picasso.get()
                .load(item.photo)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(drinkPhotoImageView)
        } else {
            Picasso.get().cancelRequest(drinkPhotoImageView)
            drinkPhotoImageView.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    companion object {
        fun from(
            parent: ViewGroup
        ) =
            DrinkInfoViewHolder(
                parent.inflate(R.layout.holder_drink_info)
            )
    }
}