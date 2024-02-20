package com.depsoftware.fgindex

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.depsoftware.fgindex.domain.model.FearGreedIndex
import com.depsoftware.fgindex.viewmodel.FearGreedIndexViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Implementation of App Widget functionality.
 */
class FearGreedIndexWidget : AppWidgetProvider(), CoroutineScope {

    private val job = SupervisorJob()
    private val viewModel = FearGreedIndexViewModel()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO.plus(job)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        launch {
            val data = viewModel.repository.getFearGreedIndexByType(FearGreedIndex.Type.ACTUAL)
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId, data)
            }
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    data: FearGreedIndex?
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.fear_greed_index_widget)
    views.setTextViewText(R.id.appwidget_text, "${data?.value}%")

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
