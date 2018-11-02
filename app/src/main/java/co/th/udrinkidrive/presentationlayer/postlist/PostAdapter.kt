package co.th.udrinkidrive.postsreader.presentation.postlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.th.udrinkidrive.R
import co.th.udrinkidrive.datalayer.entity.Post
import kotlin.properties.Delegates

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var posts by Delegates.observable(listOf<Post>()) { _, _, _ -> notifyDataSetChanged() }
    var onPostClickListener: ((id: String) -> Unit)? = null

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view, onPostClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.post = posts[position]
    }

    class ViewHolder(view: View, onPostClickListener: ((id: String) -> Unit)?) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tvPostTitle) as TextView

        var post: Post by Delegates.observable(Post()) { _, _, postItem ->
            tvTitle.text = postItem.title
        }

        init {
            view.setOnClickListener { onPostClickListener?.invoke(post.id) }
        }
    }
}

