package co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.th.udrinkidrive.R
import kotlinx.android.synthetic.main.custom_list_reward.view.*

class PostRewardAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list_reward, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tv_title_reward?.text = items[position]
        holder?.expandable_reward.toggle()
        holder?.ln_detail_reward.setOnClickListener {
            holder?.expandable_reward.toggle()
        }

    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tv_title_reward = view.tv_title_reward
    val expandable_reward = view.expandable_reward
    val ln_detail_reward = view.ln_detail_reward
}
