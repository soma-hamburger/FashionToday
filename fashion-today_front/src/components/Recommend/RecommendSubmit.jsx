import React, {
  useContext,
  useState,
  useReducer,
  useRef,
  useCallback,
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

const RecommendSubmit = ({ match }) => {
  console.log('render!!');
  const userId = match.params.userid;
  const user = useContext(UserContext);
  const RequestorCloset = useFetch(
    'post',
    'requestor/closet',
    user.token,
    JSON.stringify({
      userId,
    }),
  );

  const RequestorList = useFetch('get', 'recommend/list', user.token);

  let RequestorInfo = null;

  if (RequestorList) {
    RequestorInfo = RequestorList.data.requestor_array.find(
      r => String(r.id) === userId,
    );
  }

  const [category, setCategory] = useState(null);
  const [color, setColor] = useState(null);

  const navTool = {
    setCategory,
    setColor,
  };

  const updateLookImageData = (state, action) => {
    switch (action.type) {
      case 'add': {
        const currentImages = state.images;
        const newImage = {
          id: state.lastID,
          src: action.src,
          x: 0,
          y: 0,
          width: 75,
          height: 96,
        };

        const newImages = currentImages.concat([newImage]);
        const nextID = state.lastID + 1;

        return {
          currentID: state.lastID,
          lastID: nextID,
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

  const [LookImageData, dispatchLookImageData] = useReducer(
    updateLookImageData,
    {
      currentID: 0,
      lastID: 1,
      images: [],
    },
  );

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

  const LookImagesWindow = useRef();
  const [stageWidth, setStageWidth] = useState(250);
  const [stageHeight, setStageHeight] = useState(320);

  const ResizeStage = useCallback(() => {
    const width = LookImagesWindow.current.clientWidth;
    const height = (width * 32) / 25;
    setStageWidth(width);
    setStageHeight(height);
  }, [setStageWidth, setStageHeight]);

  useEventListener('click', ResizeStage);
  useEventListener('resize', ResizeStage);

  const handleSelect = e => {
    e.preventDefault();
    const currentId = LookImageData.currentID;
    const images = LookImageData.images.slice();
    const index = LookImageData.images.findIndex(i => i.src === e.target.src);

    if (index < 0) {
      dispatchLookImageData({ type: 'add', src: e.target.src });
    } else {
      images.splice(index, 1);
      dispatchLookImageData({ type: 'update', images, currentId });
    }
  };

  const SendLook = async () => {
    const canvas = LookImagesWindow.current.children[0].children[0].children[0];

    canvas.toBlob(async blob => {
      // eslint-disable-next-line prefer-const
      let data = new FormData();
      data.append('img', blob);
      console.log(blob);

      const res = await UserPost('look/upload', user.token, data);

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
          <form className="LookForm">
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
                />
              </div>
              <textarea
                id="Description"
                name="Description"
                type="text"
                rows="6"
                placeholder="룩 설명을 써주세요."
              />
              <button type="button" onClick={SendLook}>
                추천 완료
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};

RecommendSubmit.propTypes = {
  match: ReactRouterPropTypes.match.isRequired,
};

export default RecommendSubmit;
