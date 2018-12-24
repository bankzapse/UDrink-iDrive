package co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import co.th.udrinkidrive.R
import kotlinx.android.synthetic.main.fragment_post_change_reward.*

class PostChangeRewardFragment : Fragment() {

    lateinit var list_reward: ArrayList<String>

    companion object {
        fun newInstance() = PostChangeRewardFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_change_reward, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_reward = ArrayList()
        list_reward.add("Car insurance 3+")
        list_reward.add("Car insurance 3+")
        list_reward.add("Car insurance 3+")
        list_reward.add("Car insurance 3+")
        list_reward.add("Car insurance 3+")
        // Creates a vertical Layout Manager
        rv_list_reward.layoutManager = LinearLayoutManager(activity)

        // Access the RecyclerView Adapter and load the data into it
        rv_list_reward.adapter = PostRewardAdapter(list_reward, activity!!.applicationContext)

    }

}
