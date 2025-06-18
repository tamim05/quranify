package optimum_tech.org.pages.contacts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import optimum_tech.org.components.layouts.PageLayoutData
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initContactPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Contact Us"))
}

@Page("/contact")
@Layout(".components.layouts.PageLayout")
@Composable
fun Contacts(){
    Column {
        Text("Contact Page")
    }
}