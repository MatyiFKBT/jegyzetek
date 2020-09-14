import React, { useState, useEffect } from 'react';

function Filelist({ folder, token = '' }) {
  const [contents, setContents] = useState([]);
  const repo = 'https://raw.githubusercontent.com/MatyiFKBT/jegyzetek/master/';
  async function getContents(folder) {
    const res = await fetch(
      `http://api.github.com/repos/MatyiFKBT/jegyzetek/contents/docs/${folder}`
    );
    const text = await res.json();
    setContents(
      text.map((el) => {
        return (<a key={el} href={repo + el.path}>
          <li>{el.name}</li>
        </a>);
      })
    );
  }

  useEffect(() => {
    getContents(folder);
    return () => {
      '';
    };
  }, [folder]);

  return (
    <div>
      <ul>{contents}</ul>
    </div>
  );
}

export default Filelist