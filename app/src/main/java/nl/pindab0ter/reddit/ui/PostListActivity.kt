package nl.pindab0ter.reddit.ui

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_post_list.*
import nl.pindab0ter.reddit.R

class PostListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        actionBar?.title = "Posts for ${getString(R.string.sub_reddit)}"

        post_list.adapter = PostRecyclerViewAdapter(this)
    }

}
