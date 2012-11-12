package com.invient.vaadin;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import com.invient.vaadin.charts.Color;
import com.invient.vaadin.charts.Color.RGB;
import com.invient.vaadin.charts.Color.RGBA;
import com.invient.vaadin.charts.Gradient;
import com.invient.vaadin.charts.Gradient.LinearGradient.LinearColorStop;
import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientCharts.ChartClickEvent;
import com.invient.vaadin.charts.InvientCharts.ChartClickListener;
import com.invient.vaadin.charts.InvientCharts.ChartResetZoomEvent;
import com.invient.vaadin.charts.InvientCharts.ChartSVGAvailableEvent;
import com.invient.vaadin.charts.InvientCharts.ChartZoomEvent;
import com.invient.vaadin.charts.InvientCharts.ChartZoomListener;
import com.invient.vaadin.charts.InvientCharts.DateTimePoint;
import com.invient.vaadin.charts.InvientCharts.DateTimeSeries;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.PieChartLegendItemClickEvent;
import com.invient.vaadin.charts.InvientCharts.PointClickEvent;
import com.invient.vaadin.charts.InvientCharts.PointClickListener;
import com.invient.vaadin.charts.InvientCharts.PointRemoveEvent;
import com.invient.vaadin.charts.InvientCharts.PointSelectEvent;
import com.invient.vaadin.charts.InvientCharts.PointUnselectEvent;
import com.invient.vaadin.charts.InvientCharts.Series;
import com.invient.vaadin.charts.InvientCharts.SeriesClickEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesHideEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesLegendItemClickEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesShowEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.InvientChartsConfig.AreaConfig;
import com.invient.vaadin.charts.InvientChartsConfig.AreaSplineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitle;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitleAlign;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.DateTimePlotBand;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.DateTimePlotBand.DateTimeRange;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.Grid;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.MinorGrid;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotBand;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotBand.NumberRange;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine.NumberValue;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.PlotLabel;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.Tick;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.TickmarkPlacement;
import com.invient.vaadin.charts.InvientChartsConfig.BarConfig;
import com.invient.vaadin.charts.InvientChartsConfig.CategoryAxis;
import com.invient.vaadin.charts.InvientChartsConfig.ChartLabel;
import com.invient.vaadin.charts.InvientChartsConfig.ChartLabel.ChartLabelItem;
import com.invient.vaadin.charts.InvientChartsConfig.ColumnConfig;
import com.invient.vaadin.charts.InvientChartsConfig.DashStyle;
import com.invient.vaadin.charts.InvientChartsConfig.DataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.DateTimeAxis;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.Margin;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.Spacing;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.ZoomType;
import com.invient.vaadin.charts.InvientChartsConfig.HorzAlign;
import com.invient.vaadin.charts.InvientChartsConfig.ImageMarker;
import com.invient.vaadin.charts.InvientChartsConfig.Legend;
import com.invient.vaadin.charts.InvientChartsConfig.Legend.Layout;
import com.invient.vaadin.charts.InvientChartsConfig.LineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.MarkerState;
import com.invient.vaadin.charts.InvientChartsConfig.NumberXAxis;
import com.invient.vaadin.charts.InvientChartsConfig.NumberYAxis;
import com.invient.vaadin.charts.InvientChartsConfig.PieConfig;
import com.invient.vaadin.charts.InvientChartsConfig.PieDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.PointConfig;
import com.invient.vaadin.charts.InvientChartsConfig.Position;
import com.invient.vaadin.charts.InvientChartsConfig.ScatterConfig;
import com.invient.vaadin.charts.InvientChartsConfig.SeriesConfig;
import com.invient.vaadin.charts.InvientChartsConfig.SeriesState;
import com.invient.vaadin.charts.InvientChartsConfig.SplineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.Stacking;
import com.invient.vaadin.charts.InvientChartsConfig.SymbolMarker;
import com.invient.vaadin.charts.InvientChartsConfig.SymbolMarker.Symbol;
import com.invient.vaadin.charts.InvientChartsConfig.Tooltip;
import com.invient.vaadin.charts.InvientChartsConfig.VertAlign;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;
import com.invient.vaadin.charts.InvientChartsConfig.XAxisDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.YAxis;
import com.invient.vaadin.charts.InvientChartsConfig.YAxisDataLabel;

@SuppressWarnings("serial")
public class InvientChartsDemoBar extends Window {

    private final HorizontalSplitPanel mainSplit;
    private final VerticalLayout leftLayout;
    private final VerticalLayout rightLayout;
    private final Tree navTree;
    private TextArea eventLog = new TextArea();
    private static final String TREE_ITEM_CAPTION_PROP_ID = "ChartType";

    public InvientChartsDemoBar() {
        VerticalLayout mainLayout = new VerticalLayout();
        setContent(mainLayout);
        setSizeFull();
        mainLayout.setSizeFull();
        setCaption("Invient Charts");

        HorizontalLayout infoBar = new HorizontalLayout();
        mainLayout.addComponent(infoBar);
        infoBar.setHeight("50px");
        infoBar.setWidth("100%");
        Label lblAppTitle = new Label("Demo Gallery for Invient Charts");
        lblAppTitle.setSizeFull();
        lblAppTitle.setStyleName("v-label-app-title");
        infoBar.addComponent(lblAppTitle);

        mainSplit = new HorizontalSplitPanel();
        mainSplit.setSizeFull();
        mainLayout.addComponent(mainSplit);
        mainLayout.setExpandRatio(mainSplit, 1);

        leftLayout = new VerticalLayout();
        leftLayout.setSpacing(true);
        mainSplit.setFirstComponent(leftLayout);

        rightLayout = new VerticalLayout();
        rightLayout.setSpacing(true);
        rightLayout.setMargin(true);
        mainSplit.setSecondComponent(rightLayout);

        mainSplit.setSplitPosition(200, Sizeable.UNITS_PIXELS);

        navTree = createChartsTree();
        leftLayout.addComponent(navTree);

        eventLog.setReadOnly(true);
        eventLog.setStyleName("v-textarea-chart-events-log");
        eventLog.setSizeFull();
        eventLog.setHeight("200px");
        setTheme("chartdemo");

    }
    
    @Override
    public void attach() {
        super.attach();
        isAppRunningOnGAE = getStatisticApp().isAppRunningOnGAE();
        // Select line chart when the screen is loaded
        navTree.select(DemoSeriesType.COLUMN.getName() + SEPARATOR
                + ChartName.WITH_ROTATED_LABELS.getName());
    }

    private boolean isAppRunningOnGAE;

    public boolean isAppRunningOnGAE() {
        return isAppRunningOnGAE;
    }

    private InvientChartsDemoApp getStatisticApp() {
        return (InvientChartsDemoApp) getApplication();
    }

    private void showChart(String demoSeriesTypeName, String chartNameString) {
        if (!isAppRunningOnGAE) {
            stopSplineSelfUpdateThread();
        }
        DemoSeriesType demoSeriesType = getDemoSeriesType(demoSeriesTypeName);
        ChartName chartName = getChartName(chartNameString);
        if (demoSeriesType != null && chartName != null) {
            switch (demoSeriesType) {
            case COLUMN:
                switch (chartName) {
                case WITH_ROTATED_LABELS:
                    showColumnWithRotatedLabels();
                    break;
                }
                break;
            default:
                getApplication().getMainWindow().showNotification(
                        "Error occurred during chart processing! Try again!!!");
            }
        } else {
            getApplication().getMainWindow().showNotification(
                    "Error occurred during chart processing! Try again!!!");
        }
    }

    private Date masterChartMinDate = getDateZeroTime(2006, 0, 1);
    private Date masterChartMaxDate = getDateZeroTime(2008, 11, 31);
    private Date detailChartPointStartDate = getDateZeroTime(2008, 7, 1);

    private void showColumnWithRotatedLabels() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);
        chartConfig.getGeneralChartConfig().setMargin(new Margin());
        chartConfig.getGeneralChartConfig().getMargin().setTop(50);
        chartConfig.getGeneralChartConfig().getMargin().setRight(50);
        chartConfig.getGeneralChartConfig().getMargin().setBottom(100);
        chartConfig.getGeneralChartConfig().getMargin().setLeft(80);

        chartConfig.getTitle().setText("World\'s largest cities per 2008");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Tokyo", "Jakarta", "New York",
                "Seoul", "Manila", "Mumbai", "Sao Paulo", "Mexico City",
                "Dehli", "Osaka", "Cairo", "Kolkata", "Los Angeles",
                "Shanghai", "Moscow", "Beijing", "Buenos Aires", "Guangzhou",
                "Shenzhen", "Istanbul"));
        xAxis.setLabel(new XAxisDataLabel());
        xAxis.getLabel().setRotation(-45);
        xAxis.getLabel().setAlign(HorzAlign.RIGHT);
        xAxis.getLabel()
                .setStyle("{ font: 'normal 13px Verdana, sans-serif' }");
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setMin(0.0);
        yAxis.setTitle(new AxisTitle("Population (millions)"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig.setLegend(new Legend(false));

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.x +'</b><br/>'+ 'Population in 2008: '+ $wnd.Highcharts.numberFormat(this.y, 1) + "
                                + " ' millions' " + "}");

        InvientCharts chart = new InvientCharts(chartConfig);

        ColumnConfig colCfg = new ColumnConfig();
        colCfg.setDataLabel(new DataLabel());
        colCfg.getDataLabel().setRotation(-90);
        colCfg.getDataLabel().setAlign(HorzAlign.RIGHT);
        colCfg.getDataLabel().setX(-3);
        colCfg.getDataLabel().setY(10);
        colCfg.getDataLabel().setColor(new RGB(255, 255, 255));
        colCfg.getDataLabel().setFormatterJsFunc(
                "function() {" + " return this.y; " + "}");
        colCfg.getDataLabel().setStyle(
                " { font: 'normal 13px Verdana, sans-serif' } ");
        XYSeries seriesData = new XYSeries("Population", colCfg);
        seriesData.setSeriesPoints(getPoints(seriesData, 34.4, 21.8, 20.1, 20,
                19.6, 19.5, 19.1, 18.4, 18, 17.3, 16.8, 15, 14.7, 14.5, 13.3,
                12.8, 12.4, 11.8, 11.7, 11.2));

        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void stopSplineSelfUpdateThread() {
        if (splineThread != null) {
            synchronized (getApplication()) {
                splineThread.stopUpdating();
                indicator.setEnabled(false);
                getApplication().notifyAll();
            }
        }
    }

    private SelfUpdateSplineThread splineThread;
    private ProgressIndicator indicator;

    private class SelfUpdateSplineThread extends Thread implements Serializable {
        InvientCharts chart;

        SelfUpdateSplineThread(InvientCharts chart) {
            this.chart = chart;
        }

        private volatile boolean keepUpdating = true;

        void stopUpdating() {
            keepUpdating = false;
            System.out.println("stopUpdating " + keepUpdating);
        }

        boolean keepUpdating() {
            return keepUpdating;
        }

        @Override
        public void run() {
            while (keepUpdating()) {
                try {
                    // Sleep for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out
                            .println("InterruptedException occured. Exception message "
                                    + e.getMessage());
                }
                synchronized (getApplication()) {
                    DateTimeSeries seriesData = (DateTimeSeries) chart
                            .getSeries("Random Data");
                    seriesData.addPoint(new DateTimePoint(seriesData,
                            new Date(), Math.random()), true);
                }
                System.out.println("Inside run() keepUpdating " + keepUpdating);
            }
        }

    }

    private static Date getUpdatedDate(Date dt, long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dt.getTime() + milliseconds);
        return cal.getTime();
    }

    private void addChart(InvientCharts chart, boolean isPrepend,
            boolean isRegisterEvents) {
        addChart(chart, isPrepend, isRegisterEvents, true);
    }

    private void addChart(InvientCharts chart, boolean isPrepend,
            boolean isRegisterEvents, boolean isRegisterSVGEvent) {
        addChart(chart, isPrepend, isRegisterEvents, isRegisterSVGEvent, true);
    }

    private void addChart(InvientCharts chart, boolean isPrepend,
            boolean isRegisterEvents, boolean isRegisterSVGEvent,
            boolean isSetHeight) {
        // add events
        if (isRegisterEvents) {
            registerEvents(chart);
        }

        chart.setSizeFull();
        chart.setStyleName("v-chart-min-width");
        if (isSetHeight) {
            chart.setHeight("410px");
        }

        if (isPrepend) {
            rightLayout.setStyleName("v-chart-master-detail");
            rightLayout.addComponentAsFirst(chart);
        } else {
            rightLayout.removeStyleName("v-chart-master-detail");
            emptyEventLog();
            rightLayout.removeAllComponents();
            // Add chart
            rightLayout.addComponent(chart);
            // Add "Get SVG" button and register SVG available event
            if (isRegisterSVGEvent) {
                registerSVGAndPrintEvent(chart);
            }
            // Server events log
            rightLayout
                    .addComponent(new Label("Events received by the server:"));
            rightLayout.addComponent(eventLog);
        }
    }

    private void addChart(InvientCharts chart) {
        addChart(chart, false, true);
    }

    private void registerSVGAndPrintEvent(final InvientCharts chart) {
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setWidth("100%");
        gridLayout.setSpacing(true);
        Button svgBtn;
        gridLayout.addComponent(svgBtn = new Button("Get SVG"));
        gridLayout.setComponentAlignment(svgBtn, Alignment.MIDDLE_RIGHT);
        Button printBtn;
        gridLayout.addComponent(printBtn = new Button("Print"));
        gridLayout.setComponentAlignment(printBtn, Alignment.MIDDLE_LEFT);
        rightLayout.addComponent(gridLayout);
        svgBtn.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                chart.addListener(new InvientCharts.ChartSVGAvailableListener() {

                    @Override
                    public void svgAvailable(
                            ChartSVGAvailableEvent chartSVGAvailableEvent) {
                        logEventInfo("[svgAvailable]" + " svg -> "
                                + chartSVGAvailableEvent.getSVG());
                    }
                });
            }
        });
        printBtn.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                chart.print();
            }
        });
    }

    private void registerEvents(InvientCharts chart) {

        chart.addListener(new InvientCharts.ChartClickListener() {

            @Override
            public void chartClick(ChartClickEvent chartClickEvent) {
                logEventInfo("chartClick",
                        ((DecimalPoint) chartClickEvent.getPoint()).getX(),
                        ((DecimalPoint) chartClickEvent.getPoint()).getY(),
                        chartClickEvent.getMousePosition().getMouseX(),
                        chartClickEvent.getMousePosition().getMouseY());
            }
        });

        if (chart.getConfig().getGeneralChartConfig().getZoomType() != null) {
            chart.addListener(new InvientCharts.ChartZoomListener() {

                @Override
                public void chartZoom(ChartZoomEvent chartZoomEvent) {
                    logEventInfo("chartSelection", chartZoomEvent
                            .getChartArea().getxAxisMin(), chartZoomEvent
                            .getChartArea().getxAxisMax(), chartZoomEvent
                            .getChartArea().getyAxisMin(), chartZoomEvent
                            .getChartArea().getyAxisMax());
                }
            });

            chart.addListener(new InvientCharts.ChartResetZoomListener() {

                @Override
                public void chartResetZoom(
                        ChartResetZoomEvent chartResetZoomEvent) {
                    logEventInfo("[chartSelectionReset]");
                }
            });
        }

        chart.addListener(new InvientCharts.SeriesClickListerner() {

            @Override
            public void seriesClick(SeriesClickEvent seriesClickEvent) {
                final String EVENT_NAME = "seriesClick";
                if (seriesClickEvent.getNearestPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, seriesClickEvent.getSeries()
                            .getName(), null, (Double) seriesClickEvent
                            .getNearestPoint().getX(),
                            (Double) seriesClickEvent.getNearestPoint().getY(),
                            seriesClickEvent.getMousePosition().getMouseX(),
                            seriesClickEvent.getMousePosition().getMouseY());
                } else {
                    logEventInfo(EVENT_NAME, seriesClickEvent.getSeries()
                            .getName(), null, (Date) seriesClickEvent
                            .getNearestPoint().getX(),
                            (Double) seriesClickEvent.getNearestPoint().getY(),
                            seriesClickEvent.getMousePosition().getMouseX(),
                            seriesClickEvent.getMousePosition().getMouseY());
                }

            }
        });

        chart.addListener(new InvientCharts.SeriesHideListerner() {

            @Override
            public void seriesHide(SeriesHideEvent seriesHideEvent) {
                logEventInfo("seriesHide", seriesHideEvent.getSeries()
                        .getName());
            }
        });

        chart.addListener(new InvientCharts.SeriesShowListerner() {

            @Override
            public void seriesShow(SeriesShowEvent seriesShowEvent) {
                logEventInfo("seriesShow", seriesShowEvent.getSeries()
                        .getName());
            }
        });

        chart.addListener(new InvientCharts.SeriesLegendItemClickListerner() {

            @Override
            public void seriesLegendItemClick(
                    SeriesLegendItemClickEvent seriesLegendItemClickEvent) {
                logEventInfo("seriesLegendItemClick",
                        seriesLegendItemClickEvent.getSeries().getName());
            }
        });

        chart.addListener(new InvientCharts.PointClickListener() {

            @Override
            public void pointClick(PointClickEvent pointClickEvent) {
                final String EVENT_NAME = "pointClick";
                if (pointClickEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointClickEvent.getPoint()
                            .getSeries().getName(),
                            pointClickEvent.getCategory(),
                            (Double) pointClickEvent.getPoint().getX(),
                            (Double) pointClickEvent.getPoint().getY(),
                            pointClickEvent.getMousePosition().getMouseX(),
                            pointClickEvent.getMousePosition().getMouseY());
                } else {
                    logEventInfo(EVENT_NAME, pointClickEvent.getPoint()
                            .getSeries().getName(),
                            pointClickEvent.getCategory(),
                            (Date) pointClickEvent.getPoint().getX(),
                            (Double) pointClickEvent.getPoint().getY(),
                            pointClickEvent.getMousePosition().getMouseX(),
                            pointClickEvent.getMousePosition().getMouseY());
                }
            }
        });

        chart.addListener(new InvientCharts.PointRemoveListener() {

            @Override
            public void pointRemove(PointRemoveEvent pointRemoveEvent) {
                final String EVENT_NAME = "pointRemove";
                if (pointRemoveEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointRemoveEvent.getPoint()
                            .getSeries().getName(),
                            pointRemoveEvent.getCategory(),
                            (Double) pointRemoveEvent.getPoint().getX(),
                            (Double) pointRemoveEvent.getPoint().getY());
                } else {
                    logEventInfo(EVENT_NAME, pointRemoveEvent.getPoint()
                            .getSeries().getName(),
                            pointRemoveEvent.getCategory(),
                            (Date) pointRemoveEvent.getPoint().getX(),
                            (Double) pointRemoveEvent.getPoint().getY());
                }
            }
        });

        chart.addListener(new InvientCharts.PointSelectListener() {

            @Override
            public void pointSelected(PointSelectEvent pointSelectEvent) {
                final String EVENT_NAME = "pointSelected";
                if (pointSelectEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointSelectEvent.getCategory(),
                            (Double) pointSelectEvent.getPoint().getX(),
                            (Double) pointSelectEvent.getPoint().getY());
                } else {
                    logEventInfo(EVENT_NAME, pointSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointSelectEvent.getCategory(),
                            (Date) pointSelectEvent.getPoint().getX(),
                            (Double) pointSelectEvent.getPoint().getY());
                }
            }
        });

        chart.addListener(new InvientCharts.PointUnselectListener() {

            @Override
            public void pointUnSelect(PointUnselectEvent pointUnSelectEvent) {
                final String EVENT_NAME = "pointUnSelected";
                if (pointUnSelectEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointUnSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointUnSelectEvent.getCategory(),
                            (Double) pointUnSelectEvent.getPoint().getX(),
                            (Double) pointUnSelectEvent.getPoint().getY());
                } else {
                    logEventInfo(EVENT_NAME, pointUnSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointUnSelectEvent.getCategory(),
                            (Date) pointUnSelectEvent.getPoint().getX(),
                            (Double) pointUnSelectEvent.getPoint().getY());
                }
            }
        });

        chart.addListener(new InvientCharts.PieChartLegendItemClickListener() {

            @Override
            public void legendItemClick(
                    PieChartLegendItemClickEvent legendItemClickEvent) {
                final String EVENT_NAME = "pieLegendItemClick";
                if (legendItemClickEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, legendItemClickEvent.getPoint()
                            .getSeries().getName(), null,
                            (Double) legendItemClickEvent.getPoint().getX(),
                            (Double) legendItemClickEvent.getPoint().getY());
                }
            }
        });

    }

    private static long getPointStartDate(int year, int month, int day) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    private static Date getDateZeroTime(int year, int month, int day) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        setZeroTime(cal);
        return cal.getTime();
    }

    private static void setZeroTime(Calendar cal) {
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    private LinkedHashSet<DateTimePoint> getDateTimePoints(Series series,
            double... values) {
        LinkedHashSet<DateTimePoint> points = new LinkedHashSet<DateTimePoint>();
        for (double value : values) {
            points.add(new DateTimePoint(series, value));
        }
        return points;
    }

    private static LinkedHashSet<DecimalPoint> getPoints(Series series,
            double... values) {
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        for (double value : values) {
            points.add(new DecimalPoint(series, value));
        }
        return points;
    }

    private static LinkedHashSet<DecimalPoint> getPoints(Series series,
            double[]... values) {
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        for (double[] value : values) {
            Double x, y = null;
            if (value.length == 0)
                continue;
            if (value.length == 2) {
                x = value[0];
                y = value[1];
            } else {
                x = value[0];
            }
            points.add(new DecimalPoint(series, x, y));
        }
        return points;
    }

    private static String getFormattedTimestamp(Date dt) {
        if (dt == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
        return format.format(dt);
    }

    private static String getCurrFormattedTimestamp() {
        return getFormattedTimestamp(new Date());
    }

    private static enum ChartName {
        WITH_ROTATED_LABELS("With rotated labels");

        private String name;

        private ChartName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private ChartName getChartName(String chartNameString) {
        for (ChartName chartName : ChartName.values()) {
            if (chartNameString.equalsIgnoreCase(chartName.getName())) {
                return chartName;
            }
        }
        return null;
    }

    private static enum DemoSeriesType {
        COLUMN(SeriesType.COLUMN, "Column");

        private SeriesType seriesType;
        private String name;

        DemoSeriesType(SeriesType seriesType, String name) {
            this.seriesType = seriesType;
            this.name = name;
        }

        public SeriesType getSeriesType() {
            return this.seriesType;
        }

        public String getName() {
            return this.name;
        }

    }

    private DemoSeriesType getDemoSeriesType(String demoSeriesTypeName) {
        for (DemoSeriesType demoSeriesType : DemoSeriesType.values()) {
            if (demoSeriesTypeName.equalsIgnoreCase(demoSeriesType.getName())) {
                return demoSeriesType;
            }
        }
        return null;
    }

    private Tree createChartsTree() {
        final Tree tree = new Tree("Chart Type");
        tree.setContainerDataSource(getContainer());
        tree.setImmediate(true);
        tree.setItemCaptionPropertyId(TREE_ITEM_CAPTION_PROP_ID);
        tree.setItemCaptionMode(Tree.ITEM_CAPTION_MODE_PROPERTY);
        tree.setNullSelectionAllowed(false);

        for (Object id : tree.rootItemIds()) {
            tree.expandItemsRecursively(id);
        }

        tree.addListener(new Tree.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    Object selectedId = event.getProperty().getValue();
                    if (tree.getParent(selectedId) != null) {
                        Object parentId = tree.getParent(selectedId);
                        String demoSeriesTypeName = (String) tree
                                .getContainerProperty(parentId,
                                        TREE_ITEM_CAPTION_PROP_ID).getValue();
                        String seriesInstanceName = (String) tree
                                .getContainerProperty(selectedId,
                                        TREE_ITEM_CAPTION_PROP_ID).getValue();

                        System.out.println("parent : " + demoSeriesTypeName
                                + ", selected : " + seriesInstanceName);
                        showChart(demoSeriesTypeName, seriesInstanceName);
                    } else {
                        String demoSeriesTypeName = (String) tree
                                .getContainerProperty(selectedId,
                                        TREE_ITEM_CAPTION_PROP_ID).getValue();
                        System.out.println("Selected " + demoSeriesTypeName);
                        showChartInstancesForSeriesType(demoSeriesTypeName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return tree;
    }

    private void showChartInstancesForSeriesType(final String demoSeriesTypeName) {
        rightLayout.removeAllComponents();
        List<ChartName> demoCharts = getDemoCharts(getDemoSeriesType(demoSeriesTypeName));
        for (ChartName chartName : demoCharts) {
            Button btn = null;
            rightLayout.addComponent(btn = new Button(chartName.getName(),
                    new Button.ClickListener() {

                        @Override
                        public void buttonClick(ClickEvent event) {
                            navTree.select(demoSeriesTypeName + SEPARATOR
                                    + event.getButton().getCaption());
                        }
                    }));
            btn.setWidth("200px");
        }
    }

    private static final String SEPARATOR = "|";

    private HierarchicalContainer getContainer() {

        HierarchicalContainer container = new HierarchicalContainer();

        container.addContainerProperty(TREE_ITEM_CAPTION_PROP_ID, String.class,
                "");

        for (DemoSeriesType demoSeriesType : DemoSeriesType.values()) {
            String itemId = demoSeriesType.getName();
            Item item = container.addItem(itemId);
            item.getItemProperty(TREE_ITEM_CAPTION_PROP_ID).setValue(
                    demoSeriesType.getName());
            container.setChildrenAllowed(itemId, true);
            // add child
            addChartNamesForSeriesType(container, itemId, demoSeriesType);
        }

        return container;
    }

    private void addChartNamesForSeriesType(HierarchicalContainer container,
            Object parentId, DemoSeriesType demoSeriesType) {
        for (ChartName chartName : getDemoCharts(demoSeriesType)) {
            Object childItemId = demoSeriesType.getName() + SEPARATOR
                    + chartName.getName();
            Item childItem = container.addItem(childItemId);
            childItem.getItemProperty(TREE_ITEM_CAPTION_PROP_ID).setValue(
                    chartName.getName());
            container.setParent(childItemId, parentId);
            container.setChildrenAllowed(childItemId, false);
        }
    }

    private List<ChartName> getDemoCharts(DemoSeriesType demoSeriesType) {
        List<ChartName> chartNames = new ArrayList<ChartName>();
        switch (demoSeriesType) {
        case COLUMN:
            chartNames.add(ChartName.WITH_ROTATED_LABELS);
            break;
        }

        return chartNames;
    }

    private void logEventInfo(String eventName, String seriesName) {

        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(" series -> " + seriesName);
        logEventInfo(sb.toString());

    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Date x, Double y, Integer mouseX, Integer mouseY) {
        logStringEventInfo(eventName, seriesName, category,
                (x != null ? x.toString() : null), (y != null ? y.toString()
                        : null), (mouseX != null ? mouseX.toString() : null),
                (mouseY != null ? mouseY.toString() : null));
    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Double x, Double y, Integer mouseX, Integer mouseY) {
        logStringEventInfo(eventName, seriesName, category,
                (x != null ? x.toString() : null), (y != null ? y.toString()
                        : null), (mouseX != null ? mouseX.toString() : null),
                (mouseY != null ? mouseY.toString() : null));
    }

    private void logStringEventInfo(String eventName, String seriesName,
            String category, String x, String y, String mouseX, String mouseY) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(" series -> " + seriesName);
        if (category != null && category.length() > 0) {
            sb.append(", category -> " + category);
        }
        if (x != null) {
            sb.append(", x -> " + x);
        }
        if (y != null) {
            sb.append(", y -> " + y);
        }
        if (mouseX != null) {
            sb.append(", mouseX -> " + mouseX);
        }
        if (mouseY != null) {
            sb.append(", mouseY -> " + mouseY);
        }
        logEventInfo(sb.toString());
    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Double x, Double y) {
        logEventInfo(eventName, seriesName, category, x, y, null, null);
    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Date x, Double y) {
        logEventInfo(eventName, seriesName, category, x, y, null, null);
    }

    private void logEventInfo(String eventName, double xAxisPos,
            double yAxisPos, int mouseX, int mouseY) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(", xAxisPos -> " + xAxisPos);
        sb.append(", yAxisPos -> " + yAxisPos);
        sb.append(", mouseX -> " + mouseX);
        sb.append(", mouseY -> " + mouseY);
        logEventInfo(sb.toString());
    }

    private void logEventInfo(String eventName, double xAxisMin,
            double xAxisMax, double yAxisMin, double yAxisMax) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(", xAxisMin -> " + xAxisMin);
        sb.append(", xAxisMax -> " + xAxisMax);
        sb.append(", yAxisMin -> " + yAxisMin);
        sb.append(", yAxisMax -> " + yAxisMax);
        logEventInfo(sb.toString());
    }

    private void logEventInfo(String eventInfo) {
        logEventInfo(eventInfo, true);
    }

    private void logEventInfo(String eventInfo, boolean isAppend) {
        eventLog.setReadOnly(false);
        if (isAppend) {
            eventLog.setValue("[" + getCurrFormattedTimestamp() + "] "
                    + eventInfo + "\n" + eventLog.getValue());
        } else {
            eventLog.setValue("");
        }
        eventLog.setReadOnly(true);
    }

    private void emptyEventLog() {
        logEventInfo("", false);
    }

    private LinkedHashSet<DecimalPoint> scatterMaleData = null;
    private LinkedHashSet<DecimalPoint> scatterFemaleData = null;

}
