package nl.pindab0ter.reddit.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.post_list_item.view.*
import nl.pindab0ter.reddit.R
import nl.pindab0ter.reddit.data.Post
import nl.pindab0ter.reddit.utilities.parsePosts
import org.json.JSONObject
import kotlin.properties.Delegates

class PostRecyclerViewAdapter(context: Context) : RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

    private var posts: List<Post> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    init {
        val subReddit = context.getString(R.string.sub_reddit)
        val url = "https://www.reddit.com/$subReddit/top/.json"

        Volley.newRequestQueue(context.applicationContext).apply {
            add(JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject? ->
                posts = parsePosts(response)
            }, { error: VolleyError? ->
                error?.printStackTrace()
            }))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = posts[position].run {
        // TODO toewijze score, title en author...
    }

    override fun getItemCount() = posts.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scoreView: TextView = view.post_score
        val authorView: TextView = view.post_author
        val titleView: TextView = view.post_title
    }
}