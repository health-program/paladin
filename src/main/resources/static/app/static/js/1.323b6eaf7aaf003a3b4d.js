webpackJsonp([1],{Mq7e:function(e,t){},fjPj:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=i("Xxa5"),n=i.n(a),s=i("exGp"),o=i.n(s),r={data:function(){return{videoList:[],loading:!1,finished:!1,isLoading:!1,pageNum:1,offset:0,totalPage:0}},methods:{init:function(){var e=this;return o()(n.a.mark(function t(){var i,a,s;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return a={offset:8*((i=e).pageNum-1)},t.next=4,e.$http.get(e.$HOST+"/open/health/message/center/app/videoList",a);case 4:s=t.sent,i.videoList=s.result.videosAll.data,i.pageNum=s.result.videosAll.page,i.totalPage=Math.ceil(s.result.videosAll.total/s.result.videosAll.limit),s.result.videosAll.total<8&&(i.finished=!0,i.isLoading=!1,i.loading=!1);case 9:case"end":return t.stop()}},t,e)}))()},concatData:function(){var e=this;return o()(n.a.mark(function t(){var i,a,s;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return(i=e).pageNum+=1,a={offset:8*(i.pageNum-1)},t.next=5,e.$http.get(e.$HOST+"/open/health/message/center/app/videoList",a);case 5:s=t.sent,i.videoList=i.videoList.concat(s.result.videosAll.data),e.loading=!1,e.pageNum>=e.totalPage&&(e.finished=!0),s.result.videosAll.total<8&&(i.finished=!0,i.isLoading=!1,i.loading=!1);case 10:case"end":return t.stop()}},t,e)}))()},onRefresh:function(){var e=this;setTimeout(function(){e.$toast({message:"刷新成功",position:"bottom"}),e.isLoading=!1,e.pageNum=1,e.loading=!1,e.finished=!1,e.isLoading=!1,e.init()},500)},onLoad:function(){var e=this;setTimeout(function(){e.concatData()},500)}},created:function(){var e=this;return o()(n.a.mark(function t(){return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:e.init();case 1:case"end":return t.stop()}},t,e)}))()}},l={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{attrs:{id:"video_wrap"}},[i("new-video-slider"),e._v(" "),i("div",{staticClass:"video_tab"},[i("van-pull-refresh",{on:{refresh:e.onRefresh},model:{value:e.isLoading,callback:function(t){e.isLoading=t},expression:"isLoading"}},[i("van-list",{attrs:{finished:e.finished},on:{load:e.onLoad},model:{value:e.loading,callback:function(t){e.loading=t},expression:"loading"}},[i("ul",e._l(e.videoList,function(t,a){return i("li",{key:a},[i("div",{on:{click:function(i){e.$router.push({path:"/videoDetail",query:{id:t.id}})}}},[i("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.getImgURL(t.showImageUrl.thumbnailPelativePath),expression:"getImgURL(item.showImageUrl.thumbnailPelativePath)"}]}),e._v(" "),i("p",{staticClass:"time"},[e._v(e._s(e.timeStamp(t.publishTime)))])]),e._v(" "),i("p",{staticClass:"tit text-overflow"},[e._v(e._s(t.name))])])}))]),e._v(" "),i("div",{directives:[{name:"show",rawName:"v-show",value:e.finished,expression:"finished"}],staticClass:"no_more"},[e._v("没有更多了......")])],1)],1)],1)},staticRenderFns:[]};var d=i("VU/8")(r,l,!1,function(e){i("Mq7e")},"data-v-33f2ddd8",null);t.default=d.exports}});
//# sourceMappingURL=1.323b6eaf7aaf003a3b4d.js.map