package com.depsoftware.fgindex

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.depsoftware.fgindex.domain.model.FearGreedIndex
import com.depsoftware.fgindex.viewmodel.FearGreedIndexViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Implementation of App Widget functionality.
 */
class FearGreedIndexWidget : AppWidgetProvider() {

    private val viewModel = FearGreedIndexViewModel()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        GlobalScope.launch {
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

    // Add click for open app
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        Intent(context, MainActivity::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
