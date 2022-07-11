package androidx.navigation.fragment

import android.view.View
import androidx.navigation.Navigator

/**
 * @author ti
 * @date 2022/7/6.
 */
class FlyNavHostFragment : NavHostFragment() {

    @Deprecated("Use {@link #onCreateNavController(NavController)}")
    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        return FlyFragmentNavigator(requireContext(), childFragmentManager, containerId)
    }

    private val containerId: Int
        get() {
            // Fallback to using our own ID if this Fragment wasn't added via
            // add(containerViewId, Fragment)
            return if (id != 0 && id != View.NO_ID) id else R.id.nav_host_fragment_container
        }
}