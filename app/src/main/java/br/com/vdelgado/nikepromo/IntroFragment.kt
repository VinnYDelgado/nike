package br.com.vdelgado.nikepromo

import android.graphics.Typeface
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class IntroFragment : Fragment() {

    private var mBackgroundColor:Int = 0
    private var mPage:Int = 0

    companion object {
        private val BACKGROUND_COLOR = "background_color"
        private val PAGE = "page"

        fun newInstance(backgroundColor: Int, page: Int): IntroFragment {
            val args = Bundle()
            args.putInt(BACKGROUND_COLOR, backgroundColor)
            args.putInt(PAGE, page)
            val fragment = IntroFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments?.containsKey(BACKGROUND_COLOR) == false){
            throw RuntimeException("Fragment must contain a \"$BACKGROUND_COLOR\" argument!")
        }
        mBackgroundColor = arguments?.get(BACKGROUND_COLOR) as Int
        if(arguments?.containsKey(PAGE) == false){
            throw RuntimeException("Fragment must contain a \"$BACKGROUND_COLOR\" argument!")
        }
        mPage = arguments?.get(PAGE) as Int
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layoutResID:Int = when(mPage){
            0-> R.layout.promo_page
            1-> R.layout.promo_page
            2-> R.layout.promo_page
            else-> R.layout.promo_page
        }
        val view = activity!!.layoutInflater.inflate(layoutResID, container, false)
        view.tag = mPage
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contentBackground = view.findViewById<ConstraintLayout>(R.id.promo_page_content)
        contentBackground.setBackgroundColor(mBackgroundColor)

        val headerBackground = view.findViewById<View>(R.id.header)
        val backgroundGradient = when(mPage){
            0-> R.drawable.gradient_promo_page_one
            1-> R.drawable.gradient_promo_page_two
            else->R.drawable.gradient_promo_page_three
        }
        headerBackground.setBackgroundResource(backgroundGradient)

        val buttonBackground = view.findViewById<Button>(R.id.appCompatButton)
        val backgroundButton = when(mPage){
            0-> R.drawable.button_page_one
            1-> R.drawable.button_page_two
            else->R.drawable.button_page_three
        }
        buttonBackground.setBackgroundResource(backgroundButton)

        val typefaceRegular = Typeface.createFromAsset(context?.assets, "fonts/SanFranciscoDisplay-Regular.otf")
        val typefaceHeavy = Typeface.createFromAsset(context?.assets, "fonts/SanFranciscoDisplay-Heavy.otf")

        val textViewPrice = view.findViewById<AppCompatTextView>(R.id.textViewPrice)
        val headerDescription = view.findViewById<AppCompatTextView>(R.id.textViewHeaderDescription)
        textViewPrice.typeface = typefaceRegular
        headerDescription.typeface = typefaceRegular

        val headerTitle = view.findViewById<AppCompatTextView>(R.id.textViewHeaderTitle)
        headerTitle.typeface = typefaceHeavy
        buttonBackground.typeface = typefaceHeavy


        headerTitle.text = getString(when(mPage){
            0->R.string.title_page_one
            1->R.string.title_page_two
            else->R.string.title_page_three
        })

        headerDescription.text = getString(when(mPage){
            0->R.string.description_page_one
            1->R.string.description_page_two
            else->R.string.description_page_three
        })

        textViewPrice.text = String.format(resources.getString(R.string.price_default_product),getString(when(mPage){
            0->R.string.price_page_one
            1->R.string.price_page_two
            else->R.string.price_page_three
        }))

        val imageViewShoes = view.findViewById<AppCompatImageView>(R.id.header_imageview)
        imageViewShoes.setImageResource(when(mPage){
            0->R.drawable.nike_19
            1->R.drawable.nike_air_solstice
            else->R.drawable.nike_safari
        })



    }


}
