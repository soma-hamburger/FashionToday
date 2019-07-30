export const ClothesArray = [{
  "id": 21,
  "class": "top",
  "type": "t-shirts",
  "money": 10000,
  "color": ["blue", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 24,
  "class": "top",
  "type": "shirts",
  "money": 30000,
  "color": ["white", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 31,
  "class": "top",
  "type": "hood",
  "money": 20000,
  "color": ["orange", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 32,
  "class": "top",
  "type": "t-shirts",
  "money": 10000,
  "color": ["gray", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 121,
  "class": "bottom",
  "type": "jean",
  "money": 19000,
  "color": ["blue", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 124,
  "class": "bottom",
  "type": "pants",
  "money": 32000,
  "color": ["green", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 131,
  "class": "bottom",
  "type": "short",
  "money": 40000,
  "color": ["black", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 132,
  "class": "bottom",
  "type": "jean",
  "money": 20000,
  "color": ["blue", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
},
{
  "id": 111,
  "class": "bottom",
  "type": "skirt",
  "money": 30000,
  "color": ["red", "white"],
  "image": "http://imageurl/",
  "sub_image": ["http://imageurl/", "http://imageurl/"]
}];

export const UserInfo = {
  "id": 100000001,
  "username": "donghoon",
  "birth": 19990803,
  "profile_image": "http://imageurl/",
  "height": 170,
  "weight": 70,
  "preference": {
    "color": ["black", "blue", "white"],
    "schedule": "sports",
    "weather": "sun"
  }
}

export const LookInfo = {
  "id": 10, // unique key!
  "user": "donghoon", // userid!
  "title": "비 오는 날의 룩",
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "#fa0", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "#abc",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "#987",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "#012",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/" // look 입은 사진 url
}

export const DailyLookArray = [{
  "id": 15, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 124, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "#af0", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 122,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "#bac",
    "picture": "http://imageurl/2"
  },
  {
    "id": 119,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "#897",
    "picture": "http://imageurl/3"
  },
  {
    "id": 124,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "#ff2",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 16, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "junbeom", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "junbeom", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 324, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "#a0f", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 322,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "#baf",
    "picture": "http://imageurl/2"
  },
  {
    "id": 319,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "#879",
    "picture": "http://imageurl/3"
  },
  {
    "id": 324,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "#f2f",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 17, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "ows", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 224, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "#a0f", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 222,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "#afc",
    "picture": "http://imageurl/2"
  },
  {
    "id": 219,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "#897",
    "picture": "http://imageurl/3"
  },
  {
    "id": 224,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "#000",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
}]

export const LookArray = [{
  "id": 1, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "gray", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "blue",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "yellow",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "gold",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 2, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "sky", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "blue",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "black",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "gold",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 3, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "green", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "blue",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "black",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "gold",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 4, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "green", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "blue",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "black",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "gold",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 5, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "green", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "blue",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "black",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "gold",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 6, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "green", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "blue",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "black",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "gold",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
},
{
  "id": 7, // unique key!
  "user": "donghoon", // userid!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user || editor)!
    "recommender": "kiseong", // (user || editer)
    "weather": "sun", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "social": {
    "share": true, // boolean !
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"] // [userid]
  },
  "make_date": 20190720, // date!
  "show_date": 20190721,
  "look": [{
    "id": 20, // unique key!
    "class": "top", // (top || bottom || hat || outer || one_piece || shocks || shoes || accessories)!
    "type": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "money": 12000,
    "color": "green", // (color name or RGB code)!
    "picture": "http://imageurl/1" // image url!
  },
  {
    "id": 22,
    "class": "bottom",
    "type": "jean",
    "money": 20000,
    "color": "blue",
    "picture": "http://imageurl/2"
  },
  {
    "id": 19,
    "class": "shoes",
    "type": "running_shoes",
    "money": 55000,
    "color": "black",
    "picture": "http://imageurl/3"
  },
  {
    "id": 24,
    "class": "accessories",
    "type": "ring",
    "money": 25000,
    "color": "gold",
    "picture": "http://imageurl/4"
  }],
  "look_picture": "http://imageurl/look/1" // look 입은 사진 url
}]