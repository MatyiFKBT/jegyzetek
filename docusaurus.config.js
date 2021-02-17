const math = require('remark-math')
const katex = require('rehype-katex')

module.exports = {
  title: 'Jegyezetek',
  tagline: 'Egyetemi jegyzeteim',
  url: 'https://jegyzetek.vercel.app/',
  baseUrl: '/',
  favicon: 'img/favicon.ico',
  organizationName: 'matyifkbt', // Usually your GitHub org/user name.
  projectName: 'jegyzetek', // Usually your repo name.
  themeConfig: {
    navbar: {
      title: 'Jegyzetek',
      logo: {
        alt: 'Jegyzetek Logo',
        src: 'img/logo.svg',
      },
      items: [
        {
          to: 'docs/',
          activeBasePath: 'docs',
          label: 'Docs',
          position: 'left',
        },
        {to: 'blog', label: 'Blog', position: 'left'},
        {
          href: 'https://github.com/matyifkbt/jegyzetek',
          label: 'GitHub',
          position: 'right',
        },
        {
          type: 'docsVersionDropdown',
          position: 'right',
          //dropdownItemsAfter: [{to: '/versions', label: 'Minden'}],
          //dropdownActiveClassDisabled: true,
        }
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Jegyzetek',
          items: [
            {
              label: 'Jegyzetek',
              to: 'docs/',
            }
          ],
        },
        {
          title: 'More',
          items: [
            {
              label: 'Blog',
              to: 'blog',
            },
            {
              label: 'GitHub',
              href: 'https://github.com/matyifkbt/jegyzetek',
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} MatyiFKBT. Built with Docusaurus.`,
      
    },
    algolia: {
      apiKey: '94099ec7fd30f17f58a1fc12ced895d8',
      indexName: 'jegyzetek',
      appId: 'I8QBS59LL4'
    }
  },
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          // It is recommended to set document id as docs home page (`docs/` path).
          sidebarPath: require.resolve('./sidebars.auto.js'),
          showLastUpdateTime: true,
          // Please change this to your repo.
          editUrl:
            'https://github.com/matyifkbt/jegyzetek/edit/master/',
          remarkPlugins: [math],
          rehypePlugins: [katex],
          "lastVersion": "current",
          "versions": {
            "5felev":{
              "label": "5. félév",
              "path":"5felev"
            },
            "current":{
              "label": "6. félév",
            },
          },
          "includeCurrentVersion":true
        },
        blog: {
          showReadingTime: true,
          // Please change this to your repo.
          editUrl:
            'https://github.com/matyifkbt/jegyzetek/edit/master/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      },
    ],
  ],
  plugins: [
    'docusaurus-plugin-auto-sidebars',
  ],
  customFields: {
    repo:  "matyifkbt/jegyzetek",
  },
  stylesheets: [
    {
      href: 'https://cdn.jsdelivr.net/npm/katex@0.11.1/dist/katex.min.css',
      type: 'text/css',
      integrity: 'sha384-zB1R0rpPzHqg7Kpt0Aljp8JPLqbXI3bhnPWROx27a9N0Ll6ZP/+DiW/UqRcLbRjq',
      crossorigin: 'anonymous',
    },
  ],
};
