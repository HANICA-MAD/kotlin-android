package nl.pindab0ter.reddit.utilities

import nl.pindab0ter.reddit.data.Post
import org.json.JSONArray
import org.json.JSONObject

fun parsePosts(json: JSONObject?): List<Post> = json?.getJSONObject("data")
        ?.getJSONArray("children")
        ?.toJSONObjectList()
        ?.map(JSONObject::asPost)
        .orEmpty()

fun JSONArray.toJSONObjectList(): List<JSONObject> {
    val list = mutableListOf<JSONObject>()
    for (i in 0 until length()) {
        val item = opt(i)
        // TODO voeg item toe aan lijst.
    }
    return list
}

inline fun <reified T> JSONArray.toList(): List<T> = mutableListOf<T>().apply {
    for (i in 0 until this@toList.length()) {
        val item = opt(i)
        if (item != null && item is T) add(item as T)
    }
}

fun JSONObject.asPost(): Post = getJSONObject("data").run {
    Post(
            title = getString("title"),
            author = getString("author"),
            score = getInt("score")
    )
}