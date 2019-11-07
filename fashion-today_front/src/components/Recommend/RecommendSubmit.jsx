import React, {
  useContext,
  useState,
  useReducer,
  useRef,
  useEffect,
} from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { Image, Stage, Layer } from 'react-konva';
import useImage from 'use-image';
import { useFetch, useEventListener, UserPost } from '../../Tool';
import { UserContext } from '../../Context';
import ClosetNavigation from '../Closet/ClosetNavigation';
import Requestor from './Requestor';
import { ClickImg } from '../Common/Components';
import PlusIcon from '../../img/plus_icon.png';
import MinusIcon from '../../img/minus_icon.png';
import PinIcon from '../../img/pin_icon.png';
import RecommendCloset from './RecommendCloset';

const CanvasImage = ({
  src,
  x,
  y,
  width,
  height,
  name,
  DragStart,
  DragEnd,
}) => {
  const [image] = useImage(src);
  return (
    <Image
      draggable
      id={name}
      onDragStart={DragStart}
      onDragEnd={DragEnd}
      image={image}
      width={width}
      height={height}
      x={x}
      y={y}
    />
  );
};

const updateLookImageData = (state, action) => {
  switch (action.type) {
    case 'add': {
      const currentImages = state.images;
      const newImage = {
        id: action.id,
        src: action.src,
        x: 0,
        y: 0,
        width: action.windowWidth / 3,
        height: (action.windowWidth / 3) * (32 / 25),
      };

      const newImages = currentImages.concat([newImage]);

      return {
        currentID: action.id,
        images: newImages,
      };
    }
    case 'update': {
      return {
        ...state,
        currentID: action.id,
        images: action.images,
      };
    }
    case 'bigger': {
      const images = state.images.slice();
      const image = images.find(i => i.id === state.currentID);
      const index = images.indexOf(image);
      if (!image) {
        return state;
      }
      images[index] = {
        ...image,
        width: image.width * 1.1,
        height: image.height * 1.1,
      };
      return {
        ...state,
        images,
      };
    }
    case 'smaller': {
      const images = state.images.slice();
      const image = images.find(i => i.id === state.currentID);
      const index = images.indexOf(image);
      if (!image) {
        return state;
      }
      images[index] = {
        ...image,
        width: image.width * 0.9,
        height: image.height * 0.9,
      };
      return {
        ...state,
        images,
      };
    }
    default: {
      throw new Error(`unexpected action.type: ${action.type}`);
    }
  }
};

const RecommendSubmit = ({ match }) => {
  const userId = match.params.userid;
  const user = useContext(UserContext);

  const LookImagesWindow = useRef();

  const [LookImageData, dispatchLookImageData] = useReducer(
    updateLookImageData,
    {
      currentID: 0,
      images: [],
    },
  );

  const [RequestorInfo, setRequestorInfo] = useState(null);

  const [category, setCategory] = useState(null);
  const [color, setColor] = useState(null);

  const navTool = {
    setCategory,
    setColor,
  };

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const [stageWidth, setStageWidth] = useState(250);
  const [stageHeight, setStageHeight] = useState(320);

  const RequestorList = useFetch('get', 'recommend/list', user.token);

  const RequestorCloset = useFetch(
    'post',
    'requestor/closet',
    user.token,
    JSON.stringify({
      userId,
    }),
  );

  useEffect(() => {
    if (RequestorList) {
      setRequestorInfo(
        RequestorList.data.requestor_array.find(r => String(r.id) === userId),
      );
    }
  }, [RequestorList, userId]);

  const DragStart = e => {
    const id = e.target.id();
    const images = LookImageData.images.slice();
    const image = images.find(i => i.id === id);
    const index = images.indexOf(image);

    images.splice(index, 1);
    images.push(image);

    dispatchLookImageData({ type: 'update', images, id });
  };

  const DragEnd = e => {
    const id = e.target.id();
    const images = LookImageData.images.slice();
    const image = images.find(i => i.id === id);
    const index = images.indexOf(image);
    images[index] = {
      ...image,
      x: e.target.x(),
      y: e.target.y(),
    };
    dispatchLookImageData({ type: 'update', images, id });
  };

  const LookImages = LookImageData.images.map(img => (
    <CanvasImage
      name={img.id}
      key={img.id}
      src={img.src}
      width={img.width}
      height={img.height}
      x={img.x}
      y={img.y}
      DragStart={DragStart}
      DragEnd={DragEnd}
    />
  ));

  const ResizeStage = () => {
    const width = LookImagesWindow.current.clientWidth;
    const height = (width * 32) / 25;
    setStageWidth(width);
    setStageHeight(height);
  };

  useEventListener('click', ResizeStage);
  useEventListener('resize', ResizeStage);

  const handleSelect = e => {
    e.preventDefault();
    const currentId = LookImageData.currentID;
    const images = LookImageData.images.slice();
    const index = LookImageData.images.findIndex(i => i.src === e.target.src);

    if (index < 0) {
      const windowWidth = LookImagesWindow.current.clientWidth;
      dispatchLookImageData({
        type: 'add',
        id: e.target.alt,
        src: e.target.src,
        windowWidth,
      });
    } else {
      images.splice(index, 1);
      dispatchLookImageData({ type: 'update', images, currentId });
    }
  };

  const SendLook = async () => {
    const canvas = LookImagesWindow.current.children[0].children[0].children[0];

    const array = LookImageData.images.map(i => parseInt(i.id, 10));

    console.log(array);

    canvas.toBlob(async blob => {
      // eslint-disable-next-line prefer-const
      let data = new FormData();
      data.append('requestor_id', userId);
      data.append('date', RequestorInfo.schedule.date);
      data.append('look_img', blob);
      data.append('clothes_array', array);
      data.append('look_title', title);
      data.append('look_introduce', description);
      console.log(blob);

      const res = await UserPost('recommend', user.token, data);

      console.log(res);
    });
  };

  return (
    <div className="RecommendSubmit">
      {RequestorInfo && <Requestor requestor={RequestorInfo} />}
      {RequestorCloset && (
        <div className="RequestorCloset">
          <ClosetNavigation navTool={navTool} />
          <RecommendCloset
            category={category}
            color={color}
            lookData={LookImageData.images}
            array={RequestorCloset.data.clothes_array}
            onClick={handleSelect}
          />
          <div className="LookForm">
            <div className="LookImages" ref={LookImagesWindow}>
              <Stage width={stageWidth} height={stageHeight}>
                <Layer>{LookImages}</Layer>
              </Stage>
              <ClickImg
                src={PlusIcon}
                className="plusicon"
                onClick={() => dispatchLookImageData({ type: 'bigger' })}
              />
              <ClickImg
                src={MinusIcon}
                className="minusicon"
                onClick={() => dispatchLookImageData({ type: 'smaller' })}
              />
            </div>
            <div className="LookInfo">
              <div className="LookTitle">
                <img src={PinIcon} alt="PinIcon" />
                <input
                  id="Title"
                  name="title"
                  type="text"
                  placeholder="룩 제목"
                  autoComplete="off"
                  value={title}
                  onChange={e => setTitle(e.target.value)}
                />
              </div>
              <textarea
                id="Description"
                name="Description"
                type="text"
                rows="6"
                placeholder="룩 설명을 써주세요."
                value={description}
                onChange={e => setDescription(e.target.value)}
              />
              <button type="button" onClick={SendLook}>
                추천 완료
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

RecommendSubmit.propTypes = {
  match: ReactRouterPropTypes.match.isRequired,
};

export default RecommendSubmit;
