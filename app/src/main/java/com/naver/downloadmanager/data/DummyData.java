package com.naver.downloadmanager.data;

import com.naver.downloadmanager.data.datasource.URLData;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    public static List<URLData> getDummyData() {
        List<URLData> urls = new ArrayList<>();
        String urlData[] = {
                "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg",
                "https://www.history.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTY4ODE4ODA4MzY1MDAwNDY1/christmas-gettyimages-184652817.jpg",
                "https://api.time.com/wp-content/uploads/2019/06/what-is-half-christmas-workaholics.jpg",
                "https://www.scotsman.com/webimg/b25lY21zOjBlZjVkYThkLThjMTktNDM4Yi1hZjBhLTk2MWM3MTMyZDVlZjo4ZjhmMzY0MC1jYzBiLTQ2ZTYtOWUwMi05YmU3MTdkMTRhYjQ=.jpg",
                "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY5MDk1ODMyOTUwNTQ4MzYy/american-christmas-traditions-gettyimages-487756624.jpg",
                "https://www.thoughtco.com/thmb/EeiZ2qNO8kAdeTXZ1MUFCWiwMEQ=/1885x1414/smart/filters:no_upscale()/close-up-of-christmas-tree-898733426-5c79da8a46e0fb00018bd7fb.jpg",
                "https://static.officeholidays.com/images/1280x853c/christmas.jpg",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
//                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                "http://www.orimi.com/pdf-test.pdf",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"
        };
        for (int i = 0; i < urlData.length; i++) {
            urls.add(new URLData((i+1), urlData[i]));
        }
        return urls;
    }
}
