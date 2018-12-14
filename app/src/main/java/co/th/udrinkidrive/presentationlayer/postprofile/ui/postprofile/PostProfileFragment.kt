package co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.th.udrinkidrive.R

class PostProfileFragment : Fragment() {

    companion object {
        fun newInstance() = PostProfileFragment()
    }

    private lateinit var viewModel: PostProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.post_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostProfileViewModel::class.java)


    }

}
