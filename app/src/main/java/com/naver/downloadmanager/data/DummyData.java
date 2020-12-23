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
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                "http://www.orimi.com/pdf-test.pdf",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                "https://mir-s3-cdn-cf.behance.net/projects/404/c52c0238210507.Y3JvcCw5NTgsNzQ5LDIyMCw0NQ.png",
                "https://www.navercorp.com/img/ko/mobile/naver/img_intro.png",
                "https://image.ajunews.com/content/image/2017/01/08/20170108003419819239.jpg",
                "https://i.pinimg.com/originals/00/b6/ff/00b6ff5155e47d82103c1ffe90962115.png",
                "https://upload.wikimedia.org/wikipedia/commons/0/09/Flag_of_South_Korea.svg",
                "https://t1.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/8qDy/image/0jK-ode9iSksPtBT4FLMy2TJano.jpg",
                "https://cdn.crowdpic.net/detail-thumb/thumb_d_8AA86D4C7FE253AF0F3AF1DD63689C94.jpg",
                "https://i.ytimg.com/vi/hDa1lV8RVCA/maxresdefault.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Flag_of_the_Government_of_the_Republic_of_Korea.svg/250px-Flag_of_the_Government_of_the_Republic_of_Korea.svg.png",
                "https://cdn.pixabay.com/photo/2016/01/06/02/51/julia-roberts-1123541_960_720.jpg",
                "https://www.anztb.org/userfiles/files/API-testing-Challenges-and-Tricks-for-its-implementation.pdf",
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-zip-file.zip",
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-large-zip-file.zip",
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample.tar",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                "https://t1.daumcdn.net/liveboard/interstella-story/87c5ff6bdcf4451083e996d0189f8fb3.JPG",
                "https://i.ytimg.com/vi/W0gCJ56-2nM/maxresdefault.jpg",
                "https://www.mpps.co.kr/kfcs_api_img/KFCS/goods/DL_2173961_20200525160713616.png",
                "https://miro.medium.com/max/2560/1*2kHAAe4MpPTXMpeIeULegg.jpeg",
                "https://www.motorgraph.com/news/photo/201705/12361_58574_4435.jpg",
                "https://img.insight.co.kr/static/2018/09/03/700/gkw9s417l05aa4w69zzn.jpg",
                "https://image.fmkorea.com/files/attach/new/20190428/6761627/1954480/1771520312/c1d5fdc3199e935e0846aa8895e8480c.jpeg",
                "https://image.ajunews.com/content/image/2020/01/08/20200108105926785447.jpg",
                "https://www.autodaily.co.kr/news/photo/201912/414519_47714_4057.jpg",
                "https://image.chosun.com/sitedata/image/202005/25/2020052501688_0.jpg",
                "https://newsimg.sedaily.com/2018/09/16/1S4NQSQCSX_1.jpg",
                "https://file2.nocutnews.co.kr/newsroom/image/2020/02/21/20200221160458466333_0_795_580.jpg",
                "https://cdnweb01.wikitree.co.kr/webdata/editor/202006/17/img_20200617135720_46f04e7f.webp",
                "https://img3.yna.co.kr/etc/inner/KR/2020/12/01/AKR20201201068200005_01_i_P2.jpg"
        };
        for (int i = 0; i < urlData.length; i++) {
            urls.add(new URLData((i+1), urlData[i]));
        }
        return urls;
    }
}
