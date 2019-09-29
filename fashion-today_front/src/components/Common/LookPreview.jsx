import React from 'react';
import PropTypes from 'prop-types';
import {
  PreviewContainer,
  Image,
  LookImage,
  ProfileImage,
  UserProfile,
  UserName,
} from '../../styled';

const LookPreview = ({ lookimgurl, profileimgurl, username }) => (
  <PreviewContainer>
    <LookImage>
      <Image src={lookimgurl} />
    </LookImage>
    <UserProfile>
      <ProfileImage>
        <Image src={profileimgurl} />
      </ProfileImage>
      <UserName>{username || '장동훈'}</UserName>
    </UserProfile>
  </PreviewContainer>
);

LookPreview.propTypes = {
  lookimgurl: PropTypes.string.isRequired,
  profileimgurl: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
};

export default LookPreview;
