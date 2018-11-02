package co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.th.udrinkidrive.R

class PostMenuFragment : Fragment() {

    companion object {
        fun newInstance() = PostMenuFragment()
    }

    private lateinit var viewModel: PostMenuViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.post_menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostMenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
