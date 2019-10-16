import React, { useContext, useState, useReducer } from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { Image, Stage, Layer } from 'react-konva';
import useImage from 'use-image';
import { useFetch } from '../../Tool';
import { LoginContext } from '../../Context';
import ClosetTable from '../Closet/ClosetTable';
import ClosetNavigation from '../Closet/ClosetNavigation';

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
  const loginTool = useContext(LoginContext);
  const RequestorCloset = useFetch('requestor/closet', loginTool.token, {
    userId,
  });

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
          x: state.currentX,
          y: state.currentY,
          width: 100,
          height: 100,
        };

        const newImages = currentImages.concat([newImage]);

        let newX = state.currentX;
        let newY = state.currentY;

        if (state.currentX < 200) {
          newX += 100;
        } else {
          newX = 0;
          newY += 100;
        }
        const nextID = state.lastID + 1;

        return {
          currentID: state.lastID,
          lastID: nextID,
          currentX: newX,
          currentY: newY,
          images: newImages,
        };
      }
      case 'update': {
        console.log(state.currentID);
        console.log(action.id);
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
      currentX: 0,
      currentY: 0,
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

  console.log(LookImages);
  return (
    <div>
      {RequestorCloset && (
        <>
          {RequestorCloset.requestor_name}님의 옷장
          <br />
          <ClosetNavigation navTool={navTool} />
          <ClosetTable
            category={category}
            color={color}
            array={RequestorCloset.clothes_array}
            onClick={e => {
              e.preventDefault();
              dispatchLookImageData({ type: 'add', src: e.target.src });
            }}
          />
          <div className="LookImages">
            <Stage width={300} height={300}>
              <Layer>{LookImages}</Layer>
            </Stage>
          </div>
          <button
            type="button"
            onClick={() => dispatchLookImageData({ type: 'bigger' })}
          >
            +
          </button>
          <button
            type="button"
            onClick={() => dispatchLookImageData({ type: 'smaller' })}
          >
            -
          </button>
          <button type="submit" onClick={() => {}}>
            다운
          </button>
        </>
      )}
    </div>
  );
};

RecommendSubmit.propTypes = {
  match: ReactRouterPropTypes.match.isRequired,
};

export default RecommendSubmit;
