export const ClothesArray = [{
    "id": 20, // unique key!
    "key_word": ["Gucci", "비쌈", "카라"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "blue",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://imageurl/2", "https://imageurl/7"]
  },
  {
    "id": 19,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4", "https://imageurl/7"]
  }];

export const UserInfo = {
  "id": 100000001,
  "username": "donghoon",
  "email": "dhoonjang@gmail.com",
  "birth": 19990803,
  "profile_image": "https://imageurl/",
  "height": 170,
  "weight": 70,
  "preference": {
    "color": ["black", "blue", "white"],
    "schedule": ["sports"],
    "weather": ["sun"]
  },
  "keep_look": [ /* 킵한 룩들 (선택은 받지 못한) */]
}

export const LookInfo = {
  "id": 10, // unique key!
  "user": "donghoon", // userid!
  "title": "비 오는 날의 룩",
  "comment": "비오는 날 화사한 느낌으로 입기 좋은 룩입니다.",
  "look_picture": ["https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F25270A4C58A3D17B13", "https://imageurl/"],
  "key_word": ["비", "화사"],
  "make_date": 20190720, // date!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user)!
    "recommender": "kiseong", // (user)!
    "weather": "rain", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "look_elements": [{
    "id": 20, // unique key!
    "like": 3,
    "key_word": ["Gucci", "비쌈", "카라"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "blue",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "like": 5,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://www.jcrew.com/s7-img-facade/AB677_DM3840?fmt=jpeg&qlt=90,0&resMode=sharp&op_usm=.1,0,0,0&crop=0,0,0,0&wid=480&hei=480", "https://imageurl/7", "https://imageurl/"]
  },
  {
    "id": 19,
    "like": 5,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "like": 5,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4"]
  }],
  "social": { //선택 받기 전의 룩은 이 부분이 없음
    "share": true, // boolean !
    "wear_date": 20190721,
    //share가 false면 이 밑 부분 없음
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"], // [userid]
    "comments": [{
      "id": 222,
      "user": "ows",
      "date_time": 201908222213,
      "text": "aalidjflasjff"
    },
    {
      "id": 232,
      "user": "kiseong",
      "date_time": 201908222214,
      "text": "safdfasdfasdf"
    }]
  }
}

export const DailyLookArray = [{
  "id": 10, // unique key!
  "user": "donghoon", // userid!
  "title": "비 오는 날의 룩",
  "comment": "비오는 날 화사한 느낌으로 입기 좋은 룩입니다.",
  "look_picture": ["https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F25270A4C58A3D17B13", "https://imageurl/"],
  "key_word": ["비", "화사"],
  "make_date": 20190720, // date!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user)!
    "recommender": "kiseong", // (user)!
    "weather": "rain", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "look_elements": [{
    "id": 20, // unique key!
    "like": 3,
    "key_word": ["Gucci", "비쌈", "카라"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "blue",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "like": 5,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://www.jcrew.com/s7-img-facade/AB677_DM3840?fmt=jpeg&qlt=90,0&resMode=sharp&op_usm=.1,0,0,0&crop=0,0,0,0&wid=480&hei=480", "https://imageurl/7", "https://imageurl/"]
  },
  {
    "id": 19,
    "like": 5,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "like": 5,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4"]
  }]
},
{
  "id": 11, // unique key!
  "user": "kiseong", // userid!
  "title": "...",
  "comment": "입을 거 없을 때 입은 룩",
  "look_picture": ["https://imageurl/", "https://imageurl/"],
  "key_word": ["평범"],
  "make_date": 20190720, // date!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user)!
    "recommender": "donghoon", // (user)!
    "weather": "rain", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "look_elements": [{
    "id": 20, // unique key!
    "like": 3,
    "key_word": ["저렴", "브이넥"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "yellow",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "like": 5,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://www.jcrew.com/s7-img-facade/AB677_DM3840?fmt=jpeg&qlt=90,0&resMode=sharp&op_usm=.1,0,0,0&crop=0,0,0,0&wid=480&hei=480", "https://imageurl/7", "https://imageurl/"]
  },
  {
    "id": 19,
    "like": 5,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "like": 5,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4"]
  }],
  "social": { //선택 받기 전의 룩은 이 부분이 없음
    "share": true, // boolean !
    "wear_date": 20190721,
    //share가 false면 이 밑 부분 없음
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"], // [userid]
    "comments": [{
      "id": 222,
      "user": "ows",
      "date_time": 201908222213,
      "text": "aalidjflasjff"
    },
    {
      "id": 232,
      "user": "kiseong",
      "date_time": 201908222214,
      "text": "safdfasdfasdf"
    }]
  }
},
{
  "id": 13, // unique key!
  "user": "ows", // userid!
  "title": "햇빛 강한 날의 룩",
  "comment": "화사한 느낌으로 입기 좋은 룩입니다.",
  "look_picture": ["http://t1.daumcdn.net/liveboard/fashionn/4f16302b40654562a64313199b9c8480.jpg", "https://imageurl/"],
  "key_word": ["해", "화사"],
  "make_date": 20190720, // date!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user)!
    "recommender": "ows", // (user)!
    "weather": "rain", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "look_elements": [{
    "id": 21, // unique key!
    "like": 3,
    "key_word": ["Pollham", "카라"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "red",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "like": 5,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://www.jcrew.com/s7-img-facade/AB677_DM3840?fmt=jpeg&qlt=90,0&resMode=sharp&op_usm=.1,0,0,0&crop=0,0,0,0&wid=480&hei=480", "https://imageurl/7", "https://imageurl/"]
  },
  {
    "id": 19,
    "like": 5,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "like": 5,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4"]
  }],
  "social": { //선택 받기 전의 룩은 이 부분이 없음
    "share": true, // boolean !
    "wear_date": 20190721,
    //share가 false면 이 밑 부분 없음
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"], // [userid]
    "comments": [{
      "id": 222,
      "user": "ows",
      "date_time": 201908222213,
      "text": "aalidjflasjff"
    },
    {
      "id": 232,
      "user": "kiseong",
      "date_time": 201908222214,
      "text": "safdfasdfasdf"
    }]
  },
}];

export const LookArray = [{
  "id": 10, // unique key!
  "user": "donghoon", // userid!
  "title": "비 오는 날의 룩",
  "comment": "비오는 날 화사한 느낌으로 입기 좋은 룩입니다.",
  "look_picture": ["https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F25270A4C58A3D17B13", "https://imageurl/"],
  "key_word": ["비", "화사"],
  "make_date": 20190720, // date!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user)!
    "recommender": "kiseong", // (user)!
    "weather": "rain", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "look_elements": [{
    "id": 20, // unique key!
    "like": 3,
    "key_word": ["Gucci", "비쌈", "카라"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "blue",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "like": 5,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://www.jcrew.com/s7-img-facade/AB677_DM3840?fmt=jpeg&qlt=90,0&resMode=sharp&op_usm=.1,0,0,0&crop=0,0,0,0&wid=480&hei=480", "https://imageurl/7", "https://imageurl/"]
  },
  {
    "id": 19,
    "like": 5,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "like": 5,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4"]
  }],
  "social": { //선택 받기 전의 룩은 이 부분이 없음
    "share": true, // boolean !
    "wear_date": 20190721,
    //share가 false면 이 밑 부분 없음
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"], // [userid]
    "comments": [{
      "id": 222,
      "user": "ows",
      "date_time": 201908222213,
      "text": "aalidjflasjff"
    },
    {
      "id": 232,
      "user": "kiseong",
      "date_time": 201908222214,
      "text": "safdfasdfasdf"
    }]
  },
},
{
  "id": 11, // unique key!
  "user": "kiseong", // userid!
  "title": "...",
  "comment": "입을 거 없을 때 입은 룩",
  "look_picture": ["https://imageurl/", "https://imageurl/"],
  "key_word": ["평범"],
  "make_date": 20190720, // date!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user)!
    "recommender": "donghoon", // (user)!
    "weather": "rain", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "look_elements": [{
    "id": 20, // unique key!
    "like": 3,
    "key_word": ["저렴", "브이넥"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "yellow",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "like": 5,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://www.jcrew.com/s7-img-facade/AB677_DM3840?fmt=jpeg&qlt=90,0&resMode=sharp&op_usm=.1,0,0,0&crop=0,0,0,0&wid=480&hei=480", "https://imageurl/7", "https://imageurl/"]
  },
  {
    "id": 19,
    "like": 5,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "like": 5,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4"]
  }],
  "social": { //선택 받기 전의 룩은 이 부분이 없음
    "share": true, // boolean !
    "wear_date": 20190721,
    //share가 false면 이 밑 부분 없음
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"], // [userid]
    "comments": [{
      "id": 222,
      "user": "ows",
      "date_time": 201908222213,
      "text": "aalidjflasjff"
    },
    {
      "id": 232,
      "user": "kiseong",
      "date_time": 201908222214,
      "text": "safdfasdfasdf"
    }]
  }
},
{
  "id": 13, // unique key!
  "user": "ows", // userid!
  "title": "햇빛 강한 날의 룩",
  "comment": "화사한 느낌으로 입기 좋은 룩입니다.",
  "look_picture": ["http://t1.daumcdn.net/liveboard/fashionn/4f16302b40654562a64313199b9c8480.jpg", "https://imageurl/"],
  "key_word": ["해", "화사"],
  "make_date": 20190720, // date!
  "recommend": { // 추천에 고려된 요소와 추천 방법!
    "type": "user", //(pattern || user)!
    "recommender": "ows", // (user)!
    "weather": "rain", // (sun || rain || ~~)!
    "temperature" : 20, // (Celsius int)
    "schedule": "sports" // (wedding || sports || casual || normal ~~)
  },
  "look_elements": [{
    "id": 21, // unique key!
    "like": 3,
    "key_word": ["Pollham", "카라"],
    "purpose": ["sports", "casual"],
    "category": "t-shirts", // detail type (t-shirts || shirts || hood || ~~)
    "main_color": "red",
    "color": ["#fa0", "#abc"],// (color name or RGB code)!
    "image": ["https://imageurl/1", "https://imageurl/7"] // image url!
  },
  {
    "id": 22,
    "like": 5,
    "key_word": ["Whoau"],
    "purpose": ["sports", "casual"],
    "category": "jean",
    "main_color": "yellow",
    "color": ["#abc"],
    "image": ["https://www.jcrew.com/s7-img-facade/AB677_DM3840?fmt=jpeg&qlt=90,0&resMode=sharp&op_usm=.1,0,0,0&crop=0,0,0,0&wid=480&hei=480", "https://imageurl/7", "https://imageurl/"]
  },
  {
    "id": 19,
    "like": 5,
    "key_word": ["Lacoste"],
    "purpose": ["sports", "casual"],
    "category": "running_shoes",
    "main_color": "gray",
    "color": ["#987"],
    "image": ["https://imageurl/3", "https://imageurl/7"]
  },
  {
    "id": 24,
    "like": 5,
    "key_word": ["Gucci"],
    "purpose": ["sports", "casual"],
    "category": "bag",
    "main_color": "white",
    "color": ["#012"],
    "image": ["https://imageurl/4"]
  }],
  "social": { //선택 받기 전의 룩은 이 부분이 없음
    "share": true, // boolean !
    "wear_date": 20190721,
    //share가 false면 이 밑 부분 없음
    "like": 3, // 좋아요 수 !
    "like_user": ["donghoon", "kiseong", "ows"], // [userid]
    "comments": [{
      "id": 222,
      "user": "ows",
      "date_time": 201908222213,
      "text": "aalidjflasjff"
    },
    {
      "id": 232,
      "user": "kiseong",
      "date_time": 201908222214,
      "text": "safdfasdfasdf"
    }]
  },
}];