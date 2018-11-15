package co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp

import androidx.lifecycle.ViewModel
import co.th.udrinkidrive.datalayer.PostRepository
import co.th.udrinkidrive.util.Logger
import javax.inject.Inject

class PostOTPViewModel : ViewModel() {

    private val TAG = "show_time"

    @Inject
    lateinit var logger: Logger
    @Inject
    lateinit var postRepo: PostRepository
}
