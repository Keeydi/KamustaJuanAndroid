package com.darnayla.kamustajuan

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import com.darnayla.kamustajuan.databinding.MarkstleBinding

/**
 * Activity that shows all app screens via ViewFlipper.
 * Indices: 0=Landing, 1=Login, 2=SignUp, 3=Forgot, 4=Dashboard,
 * 5=VIP, 6=Explore, 7=Bookmark, 8=Profile, 9=Category, 10=Recommendations, 11=Popular, 12=Detail.
 */
class AuthActivity : Activity() {

    private lateinit var binding: MarkstleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MarkstleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFlipperNavigation()
        setupPasswordToggles()
        setupDashboardNavigation()
        setupDashboardContent()
        setupSimplePages()
        setupBellDropdown()
        setupPageBackButtons()
    }

    private fun showScreen(index: Int) {
        binding.viewFlipper.displayedChild = index
    }

    private fun setupFlipperNavigation() {
        val root = binding.root

        // Landing
        root.findViewById<Button>(R.id.btn_landing_get_started).setOnClickListener { showScreen(4) }
        root.findViewById<Button>(R.id.btn_landing_login).setOnClickListener { showScreen(1) }

        // Login
        root.findViewById<Button>(R.id.btn_login_back).setOnClickListener { showScreen(0) }
        root.findViewById<Button>(R.id.btn_login_go_signup).setOnClickListener { showScreen(2) }
        root.findViewById<Button>(R.id.btn_forgot_password).setOnClickListener { showScreen(3) }
        root.findViewById<Button>(R.id.btn_login_submit).setOnClickListener { showScreen(4) }

        // Sign Up
        root.findViewById<Button>(R.id.btn_signup_back).setOnClickListener { showScreen(0) }
        root.findViewById<Button>(R.id.btn_signup_go_login).setOnClickListener { showScreen(1) }
        root.findViewById<Button>(R.id.btn_signup_submit).setOnClickListener {
            val pass = root.findViewById<EditText>(R.id.signup_password).text.toString()
            val confirm = root.findViewById<EditText>(R.id.signup_confirm_password).text.toString()
            if (pass != confirm) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            showScreen(4)
        }

        // Forgot
        root.findViewById<Button>(R.id.btn_forgot_back).setOnClickListener { showScreen(1) }
        root.findViewById<Button>(R.id.btn_forgot_go_login).setOnClickListener { showScreen(1) }
        root.findViewById<Button>(R.id.btn_forgot_submit).setOnClickListener {
            Toast.makeText(this, "Reset link sent", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupDashboardNavigation() {
        val root = binding.root

        root.findViewById<android.view.View>(R.id.btn_dashboard_back).setOnClickListener { showScreen(0) }

        // VIP banner -> VIP page
        root.findViewById<android.view.View>(R.id.card_vip_banner).setOnClickListener { showScreen(5) }

        // Categories -> Category page (set title then show)
        val categoryPage = binding.viewFlipper.getChildAt(9)
        root.findViewById<android.view.View>(R.id.category_hotel).setOnClickListener {
            categoryPage.findViewById<TextView>(R.id.tv_page_title).text = getString(R.string.category_hotel)
            categoryPage.findViewById<TextView>(R.id.tv_page_desc).text = getString(R.string.card_boracay_desc)
            showScreen(9)
        }
        root.findViewById<android.view.View>(R.id.category_resort).setOnClickListener {
            categoryPage.findViewById<TextView>(R.id.tv_page_title).text = getString(R.string.category_resort)
            categoryPage.findViewById<TextView>(R.id.tv_page_desc).text = getString(R.string.card_boracay_desc)
            showScreen(9)
        }
        root.findViewById<android.view.View>(R.id.category_educational).setOnClickListener {
            categoryPage.findViewById<TextView>(R.id.tv_page_title).text = getString(R.string.category_educational)
            categoryPage.findViewById<TextView>(R.id.tv_page_desc).text = getString(R.string.card_palawan_desc)
            showScreen(9)
        }
        root.findViewById<android.view.View>(R.id.category_juan_al).setOnClickListener {
            categoryPage.findViewById<TextView>(R.id.tv_page_title).text = getString(R.string.category_juan_al)
            categoryPage.findViewById<TextView>(R.id.tv_page_desc).text = getString(R.string.card_siargao_desc)
            showScreen(9)
        }

        // See All buttons
        root.findViewById<android.view.View>(R.id.btn_recommendations_see_all).setOnClickListener { showScreen(10) }
        root.findViewById<android.view.View>(R.id.btn_popular_see_all).setOnClickListener { showScreen(11) }

        // Recommendation cards -> Detail page
        root.findViewById<android.view.View>(R.id.card_recommendation_1).setOnClickListener {
            showDetailPage(
                getString(R.string.card_boracay_title),
                getString(R.string.card_boracay_location),
                "4.8",
                getString(R.string.card_boracay_desc)
            )
        }
        root.findViewById<android.view.View>(R.id.card_recommendation_2).setOnClickListener {
            showDetailPage(
                getString(R.string.card_palawan_title),
                getString(R.string.card_palawan_location),
                "4.9",
                getString(R.string.card_palawan_desc)
            )
        }
        root.findViewById<android.view.View>(R.id.card_recommendation_3).setOnClickListener {
            showDetailPage(
                getString(R.string.card_cebu_title),
                getString(R.string.card_cebu_location),
                "4.7",
                getString(R.string.card_cebu_desc)
            )
        }
        root.findViewById<android.view.View>(R.id.card_recommendation_4).setOnClickListener {
            showDetailPage(
                getString(R.string.card_siargao_title),
                getString(R.string.card_siargao_location),
                "4.9",
                getString(R.string.card_siargao_desc)
            )
        }

        // Popular cards -> Detail page
        root.findViewById<android.view.View>(R.id.card_popular_dest_1).setOnClickListener {
            showDetailPage(
                getString(R.string.card_boracay_title),
                getString(R.string.card_boracay_location),
                "4.8",
                getString(R.string.card_boracay_desc)
            )
        }
        root.findViewById<android.view.View>(R.id.card_popular_dest_2).setOnClickListener {
            showDetailPage(
                getString(R.string.card_palawan_title),
                getString(R.string.card_palawan_location),
                "4.9",
                getString(R.string.card_palawan_desc)
            )
        }
        root.findViewById<android.view.View>(R.id.card_popular_dest_3).setOnClickListener {
            showDetailPage(
                getString(R.string.card_bohol_title),
                getString(R.string.card_bohol_location),
                "4.8",
                getString(R.string.card_bohol_desc)
            )
        }
        root.findViewById<android.view.View>(R.id.card_popular_dest_4).setOnClickListener {
            showDetailPage(
                getString(R.string.card_manila_title),
                getString(R.string.card_manila_location),
                "4.7",
                getString(R.string.card_manila_desc)
            )
        }

        // Bottom nav
        root.findViewById<android.view.View>(R.id.nav_home).setOnClickListener { showScreen(4) }
        root.findViewById<android.view.View>(R.id.nav_explore).setOnClickListener { showScreen(6) }
        root.findViewById<android.view.View>(R.id.nav_bookmark).setOnClickListener { showScreen(7) }
        root.findViewById<android.view.View>(R.id.nav_profile).setOnClickListener { showScreen(8) }
    }

    private fun setupSimplePages() {
        val flipper = binding.viewFlipper
        val titles = listOf(
            getString(R.string.upgrade_to_vip),
            getString(R.string.nav_explore),
            getString(R.string.nav_bookmark),
            getString(R.string.nav_profile),
            "Category",
            getString(R.string.recommendations),
            getString(R.string.popular_destinations)
        )
        val descs = listOf(
            getString(R.string.upgrade_to_vip_sub),
            getString(R.string.page_explore_desc),
            getString(R.string.page_bookmark_desc),
            getString(R.string.user_name),
            getString(R.string.page_category_desc),
            getString(R.string.page_recommendations_desc),
            getString(R.string.page_popular_desc)
        )
        for (i in 5..11) {
            val page = flipper.getChildAt(i)
            page.findViewById<TextView>(R.id.tv_page_title).text = titles[i - 5]
            page.findViewById<TextView>(R.id.tv_page_desc).text = descs[i - 5]
        }
    }

    private fun setupDashboardContent() {
        val root = binding.root
        // Recommendation cards: rating, title, location, desc
        val recData = listOf(
            Triple("4.8", R.string.card_boracay_title, R.string.card_boracay_location to R.string.card_boracay_desc),
            Triple("4.9", R.string.card_palawan_title, R.string.card_palawan_location to R.string.card_palawan_desc),
            Triple("4.7", R.string.card_cebu_title, R.string.card_cebu_location to R.string.card_cebu_desc),
            Triple("4.9", R.string.card_siargao_title, R.string.card_siargao_location to R.string.card_siargao_desc)
        )
        listOf(R.id.card_recommendation_1, R.id.card_recommendation_2, R.id.card_recommendation_3, R.id.card_recommendation_4).forEachIndexed { i, id ->
            val card = root.findViewById<View>(id)
            card.findViewById<TextView>(R.id.card_rating).text = recData[i].first
            card.findViewById<TextView>(R.id.card_title).text = getString(recData[i].second)
            card.findViewById<TextView>(R.id.card_location).text = getString(recData[i].third.first)
            card.findViewById<TextView>(R.id.card_desc).text = getString(recData[i].third.second)
        }
        // Popular cards
        val popData = listOf(
            "4.8" to R.string.card_boracay_title,
            "4.9" to R.string.card_palawan_title,
            "4.8" to R.string.card_bohol_title,
            "4.7" to R.string.card_manila_title
        )
        listOf(R.id.card_popular_dest_1, R.id.card_popular_dest_2, R.id.card_popular_dest_3, R.id.card_popular_dest_4).forEachIndexed { i, id ->
            val card = root.findViewById<View>(id)
            card.findViewById<TextView>(R.id.card_popular_rating).text = popData[i].first
            card.findViewById<TextView>(R.id.card_popular_title).text = getString(popData[i].second)
        }
        // Category buttons: icon + label
        val catData = listOf(
            R.drawable.ic_home to R.string.category_hotel,
            R.drawable.ic_restaurant to R.string.category_resort,
            R.drawable.ic_school to R.string.category_educational,
            R.drawable.ic_star_sparkle to R.string.category_juan_al
        )
        listOf(R.id.category_hotel, R.id.category_resort, R.id.category_educational, R.id.category_juan_al).forEachIndexed { i, id ->
            val cat = root.findViewById<View>(id)
            cat.findViewById<ImageView>(R.id.category_icon).setImageResource(catData[i].first)
            cat.findViewById<TextView>(R.id.category_label).text = getString(catData[i].second)
        }
        // Bottom nav: icon + label (home tab full opacity)
        val navData = listOf(
            R.drawable.ic_home to R.string.nav_home,
            R.drawable.ic_explore to R.string.nav_explore,
            R.drawable.ic_bookmark to R.string.nav_bookmark,
            R.drawable.ic_person_outline to R.string.nav_profile
        )
        listOf(R.id.nav_home, R.id.nav_explore, R.id.nav_bookmark, R.id.nav_profile).forEachIndexed { i, id ->
            val tab = root.findViewById<View>(id)
            tab.findViewById<ImageView>(R.id.nav_tab_icon).setImageResource(navData[i].first)
            val label = tab.findViewById<TextView>(R.id.nav_tab_label)
            label.text = getString(navData[i].second)
            label.alpha = if (i == 0) 1f else 0.9f
        }
    }

    private fun setupBellDropdown() {
        val root = binding.root
        val bell = root.findViewById<View>(R.id.btn_bell_notifications) ?: return
        bell.setOnClickListener { v ->
            val popupView = LayoutInflater.from(this).inflate(R.layout.layout_notification_dropdown, null)
            val popup = PopupWindow(
                popupView,
                resources.getDimensionPixelSize(R.dimen.notification_dropdown_width),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            ).apply {
                isOutsideTouchable = true
                setBackgroundDrawable(ColorDrawable(0))
            }
            popupView.findViewById<Button>(R.id.btn_notification_mark_all).setOnClickListener {
                popup.dismiss()
                Toast.makeText(this@AuthActivity, "Marked all as read", Toast.LENGTH_SHORT).show()
            }
            val notifData = listOf(
                "Welcome to KamustaJuan!" to "2d ago",
                "Upgrade to VIP for unlimited access" to "1d ago",
                "Palawan trending this week" to "12h ago",
                "New: Boracay recommended for you" to "2h ago",
                "Your booking was confirmed" to "Just now"
            )
            listOf(
                R.id.notification_row_1, R.id.notification_row_2, R.id.notification_row_3,
                R.id.notification_row_4, R.id.notification_row_5
            ).forEachIndexed { i, id ->
                val row = popupView.findViewById<View>(id)
                row.findViewById<TextView>(R.id.notification_item_title).text = notifData[i].first
                row.findViewById<TextView>(R.id.notification_item_time).text = notifData[i].second
                row.setOnClickListener {
                    popup.dismiss()
                    Toast.makeText(this@AuthActivity, notifData[i].first, Toast.LENGTH_SHORT).show()
                }
            }
            popup.showAsDropDown(v, -240, 8)
        }
    }

    private fun showDetailPage(title: String, location: String, rating: String, description: String) {
        val detailView = binding.viewFlipper.getChildAt(12)
        detailView.findViewById<TextView>(R.id.tv_page_detail_title).text = title
        detailView.findViewById<TextView>(R.id.detail_location).text = location
        detailView.findViewById<TextView>(R.id.detail_rating).text = rating
        detailView.findViewById<TextView>(R.id.tv_page_detail_content).text = description
        val bookmarkBtn = detailView.findViewById<ImageButton>(R.id.btn_detail_bookmark)
        bookmarkBtn.setImageResource(R.drawable.ic_bookmark)
        bookmarkBtn.setColorFilter(resources.getColor(R.color.text_primary, null))
        bookmarkBtn.setTag(R.id.btn_detail_bookmark, false)
        bookmarkBtn.setOnClickListener {
            @Suppress("DEPRECATION")
            val isBookmarked = bookmarkBtn.getTag(R.id.btn_detail_bookmark) == true
            if (isBookmarked) {
                bookmarkBtn.setImageResource(R.drawable.ic_bookmark)
                bookmarkBtn.setColorFilter(resources.getColor(R.color.text_primary, null))
                bookmarkBtn.setTag(R.id.btn_detail_bookmark, false)
                Toast.makeText(this, "Removed from bookmarks", Toast.LENGTH_SHORT).show()
            } else {
                bookmarkBtn.setImageResource(R.drawable.ic_bookmark_filled)
                bookmarkBtn.setColorFilter(resources.getColor(R.color.primary_button, null))
                bookmarkBtn.setTag(R.id.btn_detail_bookmark, true)
                Toast.makeText(this, "Added to bookmarks", Toast.LENGTH_SHORT).show()
            }
        }
        showScreen(12)
    }

    private fun setupPageBackButtons() {
        val root = binding.root
        val flipper = binding.viewFlipper
        // Simple pages (indices 5..11): back button is inside each child
        for (i in 5..11) {
            flipper.getChildAt(i).findViewById<Button>(R.id.btn_page_back)?.setOnClickListener { showScreen(4) }
        }
        root.findViewById<android.view.View>(R.id.btn_page_detail_back).setOnClickListener { showScreen(4) }
    }

    private fun setupPasswordToggles() {
        val root = binding.root
        setupToggle(
            root.findViewById(R.id.btn_login_toggle_password),
            root.findViewById(R.id.login_password)
        )
        setupToggle(
            root.findViewById(R.id.btn_signup_toggle_password),
            root.findViewById(R.id.signup_password)
        )
        setupToggle(
            root.findViewById(R.id.btn_signup_toggle_confirm_password),
            root.findViewById(R.id.signup_confirm_password)
        )
    }

    private fun setupToggle(btn: ImageButton, editText: EditText) {
        btn.setOnClickListener {
            val isCurrentlyVisible = (editText.inputType and InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) != 0
            if (isCurrentlyVisible) {
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                btn.setImageResource(R.drawable.ic_visibility_off)
                btn.contentDescription = getString(R.string.show_password)
            } else {
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                btn.setImageResource(R.drawable.ic_visibility)
                btn.contentDescription = getString(R.string.hide_password)
            }
        }
    }
}
