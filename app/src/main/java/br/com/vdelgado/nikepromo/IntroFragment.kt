package br.com.vdelgado.nikepromo

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class IntroFragment : Fragment() {

    private var mBackgroundColor: Int = 0
    private var mPage: Int = 0

    private lateinit var headerBackground: View
    private lateinit var buttonBackground: MaterialButton
    private lateinit var textViewPrice: AppCompatTextView
    private lateinit var headerDescription: AppCompatTextView
    private lateinit var headerTitle: AppCompatTextView
    private lateinit var imageViewShoes: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.containsKey(BACKGROUND_COLOR) == false) {
            throw RuntimeException("Fragment must contain a \"$BACKGROUND_COLOR\" argument!")
        }
        mBackgroundColor = arguments?.get(BACKGROUND_COLOR) as Int
        if (arguments?.containsKey(PAGE) == false) {
            throw RuntimeException("Fragment must contain a \"$BACKGROUND_COLOR\" argument!")
        }
        mPage = arguments?.get(PAGE) as Int
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layoutResID: Int = when (mPage) {
            0 -> R.layout.promo_page
            1 -> R.layout.promo_page
            2 -> R.layout.promo_page
            else -> R.layout.promo_page
        }
        val view = activity!!.layoutInflater.inflate(layoutResID, container, false)
        view.tag = mPage
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contentBackground = view.findViewById<ConstraintLayout>(R.id.promo_page_content)
        contentBackground.setBackgroundColor(mBackgroundColor)

        bindViews(view)
        initConfig()
    }

    private fun bindViews(view: View) {
        headerBackground = view.findViewById(R.id.header)
        buttonBackground = view.findViewById(R.id.appCompatButton)
        textViewPrice = view.findViewById(R.id.textViewPrice)
        headerDescription = view.findViewById(R.id.textViewHeaderDescription)
        headerTitle = view.findViewById(R.id.textViewHeaderTitle)
        imageViewShoes = view.findViewById(R.id.header_imageview)
    }

    private fun initConfig() {
        configHeaderBackground()
        configButtonBackground()
        configFonts()
        configHeaderTitle()
        configHeaderDescription()
        configPrice()
        configImageShoes()
    }

    private fun configImageShoes() {
        imageViewShoes.setImageResource(when (mPage) {
            0 -> R.drawable.nike_19
            1 -> R.drawable.nike_air_solstice
            else -> R.drawable.nike_safari
        })
    }

    private fun configPrice() {
        textViewPrice.text = String.format(resources.getString(R.string.price_default_product), getString(when (mPage) {
            0 -> R.string.price_page_one
            1 -> R.string.price_page_two
            else -> R.string.price_page_three
        }))
    }

    private fun configHeaderDescription() {
        headerDescription.text = getString(when (mPage) {
            0 -> R.string.description_page_one
            1 -> R.string.description_page_two
            else -> R.string.description_page_three
        })
    }

    private fun configHeaderTitle() {
        headerTitle.text = getString(when (mPage) {
            0 -> R.string.title_page_one
            1 -> R.string.title_page_two
            else -> R.string.title_page_three
        })
    }

    private fun configFonts() {
        val typefaceRegular = Typeface.createFromAsset(context?.assets, "fonts/SanFranciscoDisplay-Regular.otf")
        val typefaceHeavy = Typeface.createFromAsset(context?.assets, "fonts/SanFranciscoDisplay-Heavy.otf")

        textViewPrice.typeface = typefaceRegular
        headerDescription.typeface = typefaceRegular
        headerTitle.typeface = typefaceHeavy
        buttonBackground.typeface = typefaceHeavy
    }

    private fun configButtonBackground() {
        val backgroundButton = when (mPage) {
            0 -> R.color.lighter_purple
            1 -> R.color.yellow_orange
            else -> R.color.lightish_blue
        }
        context?.let {
            buttonBackground.backgroundTintList = (ContextCompat.getColorStateList(it, backgroundButton))
        }
    }

    private fun configHeaderBackground() {
        val backgroundGradient = when (mPage) {
            0 -> R.drawable.gradient_promo_page_one
            1 -> R.drawable.gradient_promo_page_two
            else -> R.drawable.gradient_promo_page_three
        }
        headerBackground.setBackgroundResource(backgroundGradient)
    }

    companion object {
        private const val BACKGROUND_COLOR = "background_color"
        private const val PAGE = "page"

        fun newInstance(backgroundColor: Int, page: Int): IntroFragment {
            val args = Bundle()
            args.putInt(BACKGROUND_COLOR, backgroundColor)
            args.putInt(PAGE, page)
            val fragment = IntroFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
