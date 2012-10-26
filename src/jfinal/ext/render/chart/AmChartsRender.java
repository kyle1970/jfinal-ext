package jfinal.ext.render.chart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jfinal.ext.render.KeyLabel;

import com.jfinal.log.Logger;
import com.jfinal.render.Render;
import com.jfinal.render.RenderException;

@SuppressWarnings("serial")
public class AmChartsRender extends Render implements AmChartsConstans {
	
	private Logger logger = Logger.getLogger(AmChartsRender.class);
	
	private static final String contentType = "text/html;charset=" + getEncoding();
	
	public  static int GLOBAL_HEIGHT = 600;
	
	public static int GLOBAL_WIDTH = 400;
	
	/** 数据源*/
	private List<?> data;
	
	/** 数据描述*/
	private List<String> series;
	
	/**饼图数据*/
	private List<KeyLabel> pies;
	
    /** 报表div的高度 */
	private  int height ;
	
	/** 报表div的宽度 */
	private  int width ;
	
	/** amcharts文件存放路径 */
	private  String path = PATH ; 
	
	/** 报表flash存放路径 */
	private  String flashFile;
	
	/** 报表settings配置文件存放的路径 */
	private  String settingsFile ;
	
	/** 报表页面的标题 */
	private  String title;

    /**  报表数据字符串*/
	private  String chartXml;
	
	/**图形类型*/
	private String type;
	
	public AmChartsRender(String type,int height,int width,String flashFile,String settingsFile){
		this.type = type;
		this.height = height;
		this.width = width;
		this.flashFile = flashFile;
		this.settingsFile = settingsFile;
	}
	

	public static AmChartsRender pie(List<KeyLabel> pies ,String flashFile,String settingsFile){
		AmChartsRender render = pie(pies,flashFile, settingsFile,0,0);
		return render;
	}
	
	public static AmChartsRender pie(List<KeyLabel> pies,String flashFile,String settingsFile,int height,int width){
		AmChartsRender render = new AmChartsRender(PIE, height>0?height:GLOBAL_HEIGHT, width>0?width:GLOBAL_WIDTH, flashFile, settingsFile);
		render.setPies(pies);
		return render;
	}
	
	public static AmChartsRender graph(List<?> data,List<String> series,String flashFile,String settingsFile){
		return graph(data,series,flashFile, settingsFile,0,0);
	}
	public static AmChartsRender graph(List<?> data,List<String> series,String flashFile,String settingsFile,int height,int width){
		AmChartsRender render = new AmChartsRender(GRAPH ,height>0?height:GLOBAL_HEIGHT, width>0?width:GLOBAL_WIDTH, flashFile, settingsFile);
		render.setData(data);
		render.setSeries(series);
		return render;
	}

	@Override
	public void render() {
		  genChartXml();
		    StringBuffer chart = new StringBuffer();
		    chart.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>amCharts Example</title></head><body style='background-color:#EEEEEE'>")
		    .append("<script type='text/javascript' src='").append(path).append("/flash/swfobject.js'></script><div id='chartdiv'></div><script type='text/javascript'>")
		    .append("var params = {bgcolor:'#FFFFFF'}; var flashVars = {path: '").append(path).append("/flash/',settings_file:  encodeURIComponent('").append(SETTINGS_FILE_BASE).append(settingsFile).append("'),")
		    .append("chart_data: \"").append(chartXml).append("\"};")
		    .append("swfobject.embedSWF('").append(path).append("/flash/").append(flashFile).append("', 'chartdiv', '").append(width).append("', '").append(height).append("', '8.0.0', '").append(path).append("/flash/expressInstall.swf', flashVars, params);</script></body></html>");
			PrintWriter writer = null;
			try {
				response.setHeader("Pragma", "no-cache");
		        response.setHeader("Cache-Control", "no-cache");
		        response.setDateHeader("Expires", 0);
				response.setContentType(contentType);
				writer = response.getWriter();
				logger.debug("chart: "+chart.toString());
				writer.write(chart.toString());
				writer.flush();
			} catch (IOException e) {
				throw new RenderException(e);
			} finally {
				writer.close();
			}
	}

	private void genChartXml() {
    	if(GRAPH.equals(type)){
    		GraphChart multipleChart = new GraphChart();
    		multipleChart.setSeriesNames(series);
    		multipleChart.setValues(data);
    		chartXml = Creater.createMultipleChart(multipleChart);
    	}else if(PIE.equals(type)){ 
    		PieChart pieChart = new PieChart();
    		if(pies==null){
//    			pies = AmChartsUtil.createKeyLabelFrom2List(amchartsSeriesNames,amchartsList);
    		}
    		pieChart.setPies(pies);
    		chartXml = Creater.createPieChart(pieChart);
    	}
    	
	}

	public static int getGLOBAL_HEIGHT() {
		return GLOBAL_HEIGHT;
	}

	public static void setGLOBAL_HEIGHT(int gLOBAL_HEIGHT) {
		GLOBAL_HEIGHT = gLOBAL_HEIGHT;
	}

	public static int getGLOBAL_WIDTH() {
		return GLOBAL_WIDTH;
	}

	public static void setGLOBAL_WIDTH(int gLOBAL_WIDTH) {
		GLOBAL_WIDTH = gLOBAL_WIDTH;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

	public List<KeyLabel> getPies() {
		return pies;
	}

	public void setPies(List<KeyLabel> pies) {
		this.pies = pies;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFlashFile() {
		return flashFile;
	}

	public void setFlashFile(String flashFile) {
		this.flashFile = flashFile;
	}

	public String getSettingsFile() {
		return settingsFile;
	}

	public void setSettingsFile(String settingsFile) {
		this.settingsFile = settingsFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChartXml() {
		return chartXml;
	}

	public void setChartXml(String chartXml) {
		this.chartXml = chartXml;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
