package com.kzdev.projetocrud.extension

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.kzdev.projetocrud.R

private val slideLefOptions = NavOptions.Builder()

    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithAnimations(
    destinationId: Int,
    animation: NavOptions = slideLefOptions,
) {
    this.navigate(destinationId, null, animation)
}