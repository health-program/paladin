webpackJsonp([8],{C33H:function(e,t){},wUN4:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=r("Xxa5"),a=r.n(n),i=r("exGp"),s=r.n(i),o={name:"detail",data:function(){return{intro:""}},watch:{$route:function(e,t){var r=this;return s()(a.a.mark(function n(){var i;return a.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:if(e.query.id===t.query.id){n.next=5;break}return n.next=3,r.$http.get(r.$HOST+"/open/health/message/center/app/play?id="+e.query.id);case 3:i=n.sent,r.intro=i.result.video;case 5:case"end":return n.stop()}},n,r)}))()}},created:function(){var e=this;return s()(a.a.mark(function t(){var r,n,i;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return r=e,n=e.$route.query.id,t.next=4,e.$http.get(e.$HOST+"/open/health/message/center/app/play?id="+n);case 4:i=t.sent,r.intro=i.result.video;case 6:case"end":return t.stop()}},t,e)}))()}},c={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{attrs:{id:"video_detail"}},[r("new-layout",[r("template",{slot:"top"},[r("video-header",[e._v(e._s(e.intro.name))]),e._v(" "),r("new-video-play")],1),e._v(" "),r("template",{slot:"center"},[r("div",{staticClass:"video_desc"},[r("div",{staticClass:"tit-date"},[r("span",[e._v("简介：")]),e._v(" "),r("span",[e._v("发布时间："+e._s(e.getYMD(e.intro.createTime)))])]),e._v(" "),r("div",{staticClass:"con"},[r("p",[e._v(e._s(e.intro.summary?e.intro.summary:"当前视频无相关简介！"))])])]),e._v(" "),r("new-favourite",{attrs:{favor:"intro"}})],1)],2)],1)},staticRenderFns:[]};var u=r("VU/8")(o,c,!1,function(e){r("C33H")},"data-v-6078f550",null);t.default=u.exports}});
//# sourceMappingURL=8.09b478b761e6774a535e.js.map