package co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.th.udrinkidrive.R

class PostEditFragment : Fragment() {

    companion object {
        fun newInstance() = PostEditFragment()
    }

    private lateinit var viewModel: PostEditViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
