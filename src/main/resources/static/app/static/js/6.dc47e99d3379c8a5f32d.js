webpackJsonp([6],{HWRu:function(e,t){},VUG8:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("Xxa5"),n=a.n(i),s=a("exGp"),o=a.n(s),r={data:function(){return{detailURL:"/videoDetail",favorList:[],loading:!1,finished:!1,isLoading:!1,pageNum:1,offset:0,totalPage:0}},methods:{init:function(){var e=this;return o()(n.a.mark(function t(){var a,i,s;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return i={offset:8*((a=e).pageNum-1)},e.$route.query.id,t.next=5,e.$http.get(e.$HOST+"/open/health/message/center/app/videoList",i);case 5:s=t.sent,console.log(s),a.favorList=s.result.videosAll.data;case 8:case"end":return t.stop()}},t,e)}))()},concatData:function(){var e=this;return o()(n.a.mark(function t(){var a,i,s;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return(a=e).pageNum+=1,i={offset:8*(a.pageNum-1)},t.next=5,e.$http.get(e.$HOST+"/open/health/message/center/app/videoList",i);case 5:s=t.sent,a.favorList=a.favorList.concat(s.result.videosAll.data),e.loading=!1,e.pageNum>=e.totalPage&&(e.finished=!0);case 9:case"end":return t.stop()}},t,e)}))()},onRefresh:function(){var e=this;setTimeout(function(){e.$toast({message:"刷新成功",position:"bottom"}),e.isLoading=!1,e.pageNum=1,e.loading=!1,e.finished=!1,e.isLoading=!1,e.init()},500)},onLoad:function(){var e=this;setTimeout(function(){e.concatData()},500)}},created:function(){this.init()}},c={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"favourite"}},[a("p",{staticClass:"tit"},[e._v("猜你喜欢")]),e._v(" "),a("van-pull-refresh",{on:{refresh:e.onRefresh},model:{value:e.isLoading,callback:function(t){e.isLoading=t},expression:"isLoading"}},[a("van-list",{attrs:{finished:e.finished},on:{load:e.onLoad},model:{value:e.loading,callback:function(t){e.loading=t},expression:"loading"}},[a("ul",{staticClass:"list"},e._l(e.favorList,function(t,i){return a("li",{key:i},[a("div",{staticClass:"left",on:{click:function(a){e.$router.push({path:e.detailURL,query:{id:t.id}})}}},[a("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.getImgURL(t.showImageUrl),expression:"getImgURL(item.showImageUrl)"}]})]),e._v(" "),a("div",{staticClass:"right"},[a("h3",{staticClass:"text-overflow",on:{click:function(a){e.$router.push({path:e.detailURL,query:{id:t.id}})}}},[e._v("\n              "+e._s(t.name)+"\n            ")]),e._v(" "),a("p",[e._v(e._s(e.getYMD(t.createTime)))])])])}))]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.finished,expression:"finished"}],staticClass:"no_more"},[e._v("没有更多了......")])],1)],1)},staticRenderFns:[]};var u=a("VU/8")(r,c,!1,function(e){a("HWRu")},"data-v-6dd8a8db",null);t.default=u.exports}});
//# sourceMappingURL=6.dc47e99d3379c8a5f32d.js.map