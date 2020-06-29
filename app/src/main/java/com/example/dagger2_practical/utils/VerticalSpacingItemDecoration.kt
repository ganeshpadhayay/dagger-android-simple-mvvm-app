package com.example.dagger2_practical.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class VerticalSpacingItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {

    fun provideItemOffsets(outRect: Rect, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect.bottom = verticalSpaceHeight
    }
}