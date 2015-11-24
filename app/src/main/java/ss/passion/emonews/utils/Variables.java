package ss.passion.emonews.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ss.passion.emonews.R;
import ss.passion.emonews.model.RssItem;
import ss.passion.emonews.model.WeatherItem;

public class Variables {
	public static final String[] CATEGORIES = { "Tin nóng", "Thế giới",
			"Xã hội", "Văn hoá", "Kinh tế", "Công nghệ", "Thể thao",
			"Giải trí", "Pháp luật", "Giáo dục", "Sức khoẻ", "Ô tô-Xe máy",
			"Nhà đất" };
	public static final int[] CATEGORY_BLACK_IMAGES = {
			R.drawable.tin_nong_black, R.drawable.the_gioi_black,
			R.drawable.xa_hoi_black, R.drawable.van_hoa_black,
			R.drawable.kinh_te_black, R.drawable.cong_nghe_black,
			R.drawable.the_thao_black, R.drawable.giai_tri_black,
			R.drawable.phap_luat_black, R.drawable.giao_duc_black,
			R.drawable.suc_khoe_black, R.drawable.oto_xemay_black,
			R.drawable.nha_dat_black };
	public static final int[] CATEGORY_COLOR_IMAGES = {
			R.drawable.tin_nong_color, R.drawable.the_gioi_color,
			R.drawable.xa_hoi_color, R.drawable.van_hoa_color,
			R.drawable.kinh_te_color, R.drawable.cong_nghe_color,
			R.drawable.the_thao_color, R.drawable.giai_tri_color,
			R.drawable.phap_luat_color, R.drawable.giao_duc_color,
			R.drawable.suc_khoe_color, R.drawable.oto_xemay_color,
			R.drawable.nha_dat_color };

	// category links
	public static final String[] TIN_NONG_LINKS = { "http://www.baomoi.com/Rss/RssFeed.ashx" };
	public static final String[] THE_GIOI_LINKS = { "http://www.baomoi.com/Home/TheGioi.rss" };
	public static final String[] XA_HOI_LINKS = { "http://www.baomoi.com/Home/XaHoi.rss" };
	public static final String[] VAN_HOA_LINKS = { "http://www.baomoi.com/Home/VanHoa.rss" };
	public static final String[] KINH_TE_LINKS = { "http://www.baomoi.com/Home/KinhTe.rss" };
	public static final String[] CONG_NGHE_LINKS = { "http://www.baomoi.com/Home/KHCN.rss" };
	public static final String[] THE_THAO_LINKS = { "http://www.baomoi.com/Home/TheThao.rss" };
	public static final String[] GIAI_TRI_LINKS = { "http://www.baomoi.com/Home/GiaiTri.rss" };
	public static final String[] PHAP_LUAT_LINKS = { "http://www.baomoi.com/Home/PhapLuat.rss" };
	public static final String[] GIAO_DUC_LINKS = { "http://www.baomoi.com/Home/GiaoDuc.rss" };
	public static final String[] SUC_KHOE_LINKS = { "http://www.baomoi.com/Home/SucKhoe.rss" };
	public static final String[] OTO_XEMAY_LINKS = { "http://www.baomoi.com/Home/OtoXemay.rss" };
	public static final String[] NHA_DAT_LINKS = { "http://www.baomoi.com/Home/NhaDat.rss" };

	// All links
	public static final String[][] LINKS = { TIN_NONG_LINKS, THE_GIOI_LINKS,
			XA_HOI_LINKS, VAN_HOA_LINKS, KINH_TE_LINKS, CONG_NGHE_LINKS,
			THE_THAO_LINKS, GIAI_TRI_LINKS, PHAP_LUAT_LINKS, GIAO_DUC_LINKS,
			SUC_KHOE_LINKS, OTO_XEMAY_LINKS, NHA_DAT_LINKS };

	public static final String CATEGORY_PRE = "category_pre";
	public static final String CATEGORY_KEY = "category";
	public static final String NEWS_ID = "news_id";
	public static final String NEWS_URL = "news_url";

	public static int CATEGORY_ID;

	public static HashMap<Integer, List<RssItem>> newsMap = new HashMap<Integer, List<RssItem>>();
	public static List<WeatherItem> weatherList= new ArrayList<WeatherItem>();

	// Navigaion Drawer Items
	public static final String[] DRAWER_TITLES = { "Tin tức", "Thời tiết",
			"Trợ giúp"};
	public static final int[] DRAWER_ICONS = { R.drawable.tin_tuc,
			R.drawable.thoi_tiet, R.drawable.tro_giup };

	// Weather background
	public static final String[] WEATHER_CHECK = { "snow", "rain", "shower",
			"drizzle", "fog", "mist", "overcast", "cloudy", "sunny" };
	public static final int[] WEATHER_BACK = { R.drawable.snow,
			R.drawable.light_rain, R.drawable.heavy_rain, R.drawable.drizzle,
			R.drawable.fog, R.drawable.fog, R.drawable.overcast,
			R.drawable.cloudy, R.drawable.sunny };

}
