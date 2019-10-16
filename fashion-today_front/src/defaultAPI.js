/* eslint-disable */
export const UserState = {
  remark: 'success',
  is_select: false,
  user_id: 10000001,
  name: '장동훈',
  star: 8,
  profile_image: 'https://imageurl/profile/:id',
  msec: 10,
  apiseq: 200,
};

export const UserInfo = {
  remark: 'success',
  user_id: 10000002,
  name: '심기성',
  birth: 19941105,
  gender: 'male',
  profile_image: 'https://imageurl/profile/:id',
  self_introduction: '심퀴입니다 ㅎㅎ',
  star: 10,
  grade: 20,
  msec: 10,
  apiseq: 201,
};

export const UserCloset = {
  remark: 'success',
  user_id: 10000001,
  clothes_num: 3,
  clothes_array: [
    {
      clothes_id: 20000001,
      color: 'red',
      category: 'jean',
      clothes_image: 'https://imageurl/clothes/:id',
    },
    {
      clothes_id: 20000002,
      color: 'red',
      category: 'tee',
      clothes_image: 'https://imageurl/clothes/:id',
    },
    {
      clothes_id: 20000003,
      color: 'blue',
      category: 'shirts',
      clothes_image: 'https://imageurl/clothes/:id',
    },
  ],
  msec: 10,
  apiseq: 202,
};

export const getDailyLookList = {
  remark: 'success',
  user_id: 10000001,
  look_num: 3,
  star: 3,
  daily_look_array: [
    {
      look_id: 30000001,
      recommender_id: 10000000,
      recommender_name: '컴퓨터',
      recommender_profile_image: 'https://imageurl/profile/:id',
      look_image: 'https://imageurl/looks/:id',
    },
    {
      look_id: 30000002,
      recommender_id: 10000002,
      recommender_name: '심기성',
      recommender_profile_image: 'https://imageurl/profile/:id',
      look_image: 'https://imageurl/looks/:id',
    },
    {
      look_id: 30000003,
      recommender_id: 10000002,
      recommender_name: '심기성',
      recommender_profile_image: 'https://imageurl/profile/:id',
      look_image: 'https://imageurl/looks/:id',
    },
  ],
  msec: 10,
  apiseq: 203,
};

export const DailyLookDetail = {
  remark: 'success',
  look_id: 30000002,
  recommender: {
    id: 10000002,
    name: '심기성',
    profile_image: 'https://imageurl/profile/:id',
    grade: 30,
  },
  look_image: 'https://imageurl/looks/:id',
  look_title: '단정한 룩',
  look_introduction: '면접을 위해 단정하게 입기 좋은 룩입니다.',
  clothes_array: [
    {
      clothes_id: 20000001,
      color: 'red',
      category: 'jean',
      clothes_image: 'https://imageurl/clothes/:id',
    },
    {
      clothes_id: 20000002,
      color: 'red',
      category: 'tee',
      clothes_image: 'https://imageurl/clothes/:id',
    },
  ],
  msec: 10,
  apiseq: 204,
};

export const UserScheduleList = {
  remark: 'success',
  user_id: 10000001,
  schedule_num: 3,
  schedule_array: [
    {
      date: 20190810,
      state: 'past',
      star_num: 3,
    },
    {
      date: 20190812,
      state: 'present',
      star_num: 2,
    },
    {
      date: 20190816,
      state: 'future',
      star_num: 5,
    },
  ],
  msec: 10,
  apiseq: 205,
};

export const UserScheduleDetail = {
  remark: 'success',
  user_id: 10000001,
  date: 20190810,
  state: 'past',
  star_num: 5,
  schedule_title: '면접',
  schedule_introduction: '중요한 면접이 있어 단정하게 입고 싶습니다.',
  look: {
    id: 30000002,
    share: false,
    recommender: {
      id: 10000002,
      name: '심기성',
      profile_image: 'https://imageurl/profile/:id',
      grade: 30,
    },
    look_image: 'https://imageurl/looks/:id',
    look_title: '단정한 룩',
    look_introduction: '면접을 위해 단정하게 입기 좋은 룩입니다.',
    clothes_array: [
      {
        clothes_id: 20000001,
        color: 'red',
        category: 'jean',
        clothes_image: 'https://imageurl/clothes/:id',
      },
      {
        clothes_id: 20000002,
        color: 'red',
        category: 'tee',
        clothes_image: 'https://imageurl/clothes/:id',
      },
    ],
  },
  msec: 10,
  apiseq: 206,
};

export const LookListInfo = {
  remark: 'success',
  look_num: 3,
  look_array: [
    {
      look_id: 30000001,
      user_id: 10000001,
      user_name: '장동훈',
      user_profile_image: 'https://imageurl/profile/:id',
      look_image: 'https://imageurl/looks/:id',
    },
    {
      look_id: 30000002,
      user_id: 10000002,
      user_name: '심기성',
      user_profile_image: 'https://imageurl/profile/:id',
      look_image: 'https://imageurl/looks/:id',
    },
    {
      look_id: 30000003,
      user_id: 10000003,
      user_name: '오원석',
      user_profile_image: 'https://imageurl/profile/:id',
      look_image: 'https://imageurl/looks/:id',
    },
  ],
  msec: 10,
  apiseq: 207,
};

export const LookDetail = {
  remark: 'success',
  user_id: 10000001,
  user_name: '장동훈',
  look_id: 30000002,
  like_num: 5,
  recommender: {
    id: 10000002,
    name: '심기성',
    profile_image: 'https://imageurl/profile/:id',
    grade: 30,
  },
  look_image: 'https://imageurl/looks/:id',
  look_subimage: 'https://imageurl/looks/:id',
  look_title: '단정한 룩',
  look_introduction: '면접을 위해 단정하게 입기 좋은 룩입니다.',
  clothes_array: [
    {
      clothes_id: 20000001,
      color: 'red',
      category: 'jean',
      clothes_image: 'https://imageurl/clothes/:id',
    },
    {
      clothes_id: 20000002,
      color: 'red',
      category: 'tee',
      clothes_image: 'https://imageurl/clothes/:id',
    },
  ],
  msec: 10,
  apiseq: 208,
};

export const LookRequestorList = {
  remark: 'success',
  requestor_array: [
    {
      //한 번에 5개
      id: 10000001,
      name: '장동훈',
      self_introduction: '장동훈입니다 ㅎㅎ',
      profile_image: 'https://imageurl/profile/:id',
      schedule: {
        date: 20190810,
        star_num: 5,
        look_num: 3,
        schedule_title: '면접',
        schedule_introduction: '중요한 면접이 있어 단정하게 입고 싶습니다.',
      },
    },
    {
      id: 10000002,
      name: '장동훈',
      self_introduction: '장동훈입니다 ㅎㅎ',
      profile_image: 'https://imageurl/profile/:id',
      schedule: {
        date: 20190810,
        star_num: 5,
        look_num: 3,
        schedule_title: '면접',
        schedule_introduction: '중요한 면접이 있어 단정하게 입고 싶습니다.',
      },
    },
    {
      id: 10000003,
      name: '장동훈',
      self_introduction: '장동훈입니다 ㅎㅎ',
      profile_image: 'https://imageurl/profile/:id',
      schedule: {
        date: 20190810,
        star_num: 5,
        look_num: 3,
        schedule_title: '면접',
        schedule_introduction: '중요한 면접이 있어 단정하게 입고 싶습니다.',
      },
    },
    {
      id: 10000004,
      name: '장동훈',
      self_introduction: '장동훈입니다 ㅎㅎ',
      profile_image: 'https://imageurl/profile/:id',
      schedule: {
        date: 20190810,
        star_num: 5,
        look_num: 3,
        schedule_title: '면접',
        schedule_introduction: '중요한 면접이 있어 단정하게 입고 싶습니다.',
      },
    },
    {
      id: 10000005,
      name: '장동훈',
      self_introduction: '장동훈입니다 ㅎㅎ',
      profile_image: 'https://imageurl/profile/:id',
      schedule: {
        date: 20190810,
        star_num: 5,
        look_num: 3,
        schedule_title: '면접',
        schedule_introduction: '중요한 면접이 있어 단정하게 입고 싶습니다.',
      },
    },
  ],
  msec: 10,
  apiseq: 209,
};

export const RequestorCloset = {
  remark: 'success',
  requestor_id: 10000001,
  requestor_name: '장동훈',
  clothes_num: 3,
  clothes_array: [
    {
      clothes_id: 20000001,
      color: 'red',
      category: 'jean',
      clothes_image:
        'https://images.houseoffraser.co.uk/images/products/29017969_3pl.jpg',
    },
    {
      clothes_id: 20000002,
      color: 'red',
      category: 'tee',
      clothes_image:
        'https://www.pinpng.com/pngs/m/197-1976292_aesthetic-tumblr-clothes-png-transparent-png.png',
    },
    {
      clothes_id: 20000003,
      color: 'blue',
      category: 'shirts',
      clothes_image:
        'https://imgs.clipartwiki.com/clipimg/small/177-1771708_folded-clothes-png-aesthetic-boy-clothes-png.png',
    },
  ],
  msec: 10,
  apiseq: 210,
};
