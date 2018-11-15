package co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.th.udrinkidrive.R

class PostOTPFragment : Fragment() {

    companion object {
        fun newInstance() = PostOTPFragment()
    }

    private lateinit var viewModel: PostOTPViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.post_otp_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostOTPViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
