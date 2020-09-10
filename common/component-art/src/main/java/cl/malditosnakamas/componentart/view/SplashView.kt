package cl.malditosnakamas.componentart.view

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import com.airbnb.lottie.LottieAnimationView

class SplashView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : LottieAnimationView(context, attrs, defStyleAttr) {
    init {
        setAnimation("splash.json")
        playAnimation()
        repeatCount = ValueAnimator.INFINITE
    }
}