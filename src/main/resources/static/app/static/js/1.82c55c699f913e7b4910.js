webpackJsonp([1],{"5t3n":function(e,t){},fjPj:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("Xxa5"),n=a.n(i),s=a("exGp"),r=a.n(s),o={data:function(){return{searchShow:!0,value:"",videoList:[],loading:!1,finished:!1,isLoading:!1,pageNum:1,offset:0,totalPage:0,label:""}},computed:{filterResult:function(){var e=this;return this.videoList.filter(function(t){return new RegExp(e.value,"i").test(t)})}},methods:{search:function(){var e=this;return r()(n.a.mark(function t(){var a,i,s;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return(a=e).pageNum=1,i={offset:8*(a.pageNum-1),label:a.label},t.next=5,e.$http.get(e.$HOST+"/open/health/message/center/video/search/find",i);case 5:if(0!=(s=t.sent).result.total){t.next=10;break}a.videoList=[],t.next=18;break;case 10:if(a.videoList=s.result.data,a.pageNum=s.result.page,a.totalPage=Math.ceil(s.result.total/s.result.limit),!(s.result.total<8)){t.next=18;break}return a.finished=!0,a.isLoading=!1,a.loading=!1,t.abrupt("return",!1);case 18:case"end":return t.stop()}},t,e)}))()},init:function(){var e=this;return r()(n.a.mark(function t(){var a,i,s;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return i={offset:8*((a=e).pageNum-1)},t.next=4,e.$http.get(e.$HOST+"/open/health/message/center/app/videoList",i);case 4:if(0==(s=t.sent).result.videosAll.total?e.searchShow=!1:e.searchShow=!0,a.videoList=s.result.videosAll.data,a.pageNum=s.result.videosAll.page,a.totalPage=Math.ceil(s.result.videosAll.total/s.result.videosAll.limit),!(s.result.videosAll.total<8)){t.next=14;break}return a.finished=!0,a.isLoading=!1,a.loading=!1,t.abrupt("return",!1);case 14:case"end":return t.stop()}},t,e)}))()},concatData:function(){var e=this;return r()(n.a.mark(function t(){var a,i,s;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return(a=e).pageNum+=1,i={offset:8*(a.pageNum-1)},t.next=5,e.$http.get(e.$HOST+"/open/health/message/center/app/videoList",i);case 5:if(!((s=t.sent).result.videosAll.total<8)){t.next=11;break}return a.finished=!0,a.isLoading=!1,a.loading=!1,t.abrupt("return",!1);case 11:a.videoList=a.videoList.concat(s.result.videosAll.data),e.loading=!1,e.pageNum>=e.totalPage&&(a.finished=!0,a.isLoading=!1,a.loading=!1);case 14:case"end":return t.stop()}},t,e)}))()},onRefresh:function(){var e=this;setTimeout(function(){e.$toast({message:"刷新成功",position:"bottom"}),e.isLoading=!1,e.pageNum=1,e.loading=!1,e.finished=!1,e.isLoading=!1,e.init()},500)},onLoad:function(){var e=this;setTimeout(function(){e.concatData()},500)}},created:function(){var e=this;return r()(n.a.mark(function t(){return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:e.init();case 1:case"end":return t.stop()}},t,e)}))()}},l={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"video_wrap"}},[a("new-video-slider"),e._v(" "),a("div",{staticClass:"video_tab"},[e.searchShow?a("div",{staticClass:"search bar"},[a("div",{staticClass:"form"},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.label,expression:"label"}],attrs:{placeholder:"请输入搜索",name:"cname",type:"text"},domProps:{value:e.label},on:{input:function(t){t.target.composing||(e.label=t.target.value)}}}),e._v(" "),a("span",{staticClass:"seahchspan",on:{click:function(t){e.search()}}},[e._v("搜索")])])]):e._e(),e._v(" "),a("br"),e._v(" "),a("br"),e._v(" "),a("br"),e._v(" "),a("van-pull-refresh",{on:{refresh:e.onRefresh},model:{value:e.isLoading,callback:function(t){e.isLoading=t},expression:"isLoading"}},[a("van-list",{attrs:{finished:e.finished},on:{load:e.onLoad},model:{value:e.loading,callback:function(t){e.loading=t},expression:"loading"}},[a("ul",e._l(e.videoList,function(t,i){return a("li",{key:i},[a("div",{on:{click:function(a){e.$router.push({path:"/videoDetail",query:{id:t.id}})}}},[a("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.getImgURL(t.showImageUrl.thumbnailPelativePath),expression:"getImgURL(item.showImageUrl.thumbnailPelativePath)"}]}),e._v(" "),a("p",{staticClass:"time"},[e._v(e._s(e.timeStamp(t.publishTime)))])]),e._v(" "),a("p",{staticClass:"tit text-overflow"},[e._v(e._s(t.name))])])}))]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.finished,expression:"finished"}],staticClass:"no_more"},[e._v("没有更多了......")])],1)],1)],1)},staticRenderFns:[]};var u=a("VU/8")(o,l,!1,function(e){a("5t3n")},"data-v-690c5ae8",null);t.default=u.exports}});
//# sourceMappingURL=1.82c55c699f913e7b4910.js.map