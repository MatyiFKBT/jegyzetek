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
              href: 'https://github.com/facebook/docusaurus',
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} MatyiFKBT. Built with Docusaurus.`,
    },
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
};
