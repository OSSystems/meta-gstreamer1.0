OpenEmbedded  layer for GStreamer 1.0
=====================================

This layer provides UNOFFICIAL support for the
[GStreamer 1.0 framework](http://gstreamer.freedesktop.org/) for use with
OpenEmbedded and/or Yocto.

The recipes are adapted versions of the GStreamer 0.10 recipes from OE-core.
gst-openmax was replaced with gst-omx, since gst-openmax is unmaintained and
has not been ported to 1.0 (gst-omx is its successor). gst-ffmpeg was replaced
by gst-libav.

These recipes have been pushed upstream to OpenEmbedded Core, and will probably
be part of the next OE version. This layer therefore will be used to provide
support for older versions (dylan and danny). If possible and appropiate,
patches from OE core will be backported to this layer.


Dependencies
------------

* URI: git://git.openembedded.org/openembedded-core
* branch: master
* revision: HEAD


Package names and coexistence with 0.10
---------------------------------------

OpenEmbedded Core currently contains recipes for GStreamer 0.10.36 . Since 1.0
was designed to be able to coexist with 0.10 in the same system, the recipes
for GStreamer 1.0 use different names. These are:

* gstreamer1.0
* gstreamer1.0-plugins-base
* gstreamer1.0-plugins-good
* gstreamer1.0-plugins-bad
* gstreamer1.0-plugins-ugly
* gstreamer1.0-libav
* gstreamer1.0-omx


Building git versions
---------------------

By default, release tarballs are unpacked and built. It is possible, however, to build from the GStreamer
git repositories instead. It is generally better to use the releases, however for bleeding edge features
the git versions might be necessary.

To let Bitbake know that the git version of a package is preferred, add to local.conf:

    PREFERRED_VERSION_<packagename> = "git"

For example, to use git versions of all packages, add to local.conf:

    PREFERRED_VERSION_gstreamer1.0 = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-base = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-good = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-bad = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-ugly = "git"
    PREFERRED_VERSION_gstreamer1.0-libav = "git"
    PREFERRED_VERSION_gstreamer1.0-omx = "git"


Enabling plugins with dependencies
----------------------------------

By default, in the base/good/bad/ugly recipes, only dependency-less plugins and plugins with dependencies
that are supported by OE-core (i.e. recipes for them exist in OE-core) are always enabled.

These are:
* gstreamer1.0-plugins-base : ivorbis (Tremor), ogg, theora, vorbis
* gstreamer1.0-plugins-good : cairo, flac, gdk-pixbuf, jpeg, libpng, soup, speex, taglib
* gstreamer1.0-plugins-bad : curl, uvch264, neon, sndfile, hls, sbc, dash, bz2, smoothstreaming
* gstreamer1.0-plugins-ugly : a52dec, lame, mad, mpeg2dec

With the X11, Wayland, ALSA, BlueZ, DirectFB, OpenGL, and PulseAudio plugins, the situation is a bit different.
They are built depending on the contents of the DISTRO\_FEATURES value (in other words, depending on what the OE
distribution supports).

The rest is disabled by default, and can be enabled by appending to the respective PACKAGECONFIG values.
For example, to enable vpx and wavpack support in gstreamer1.0-plugins-good , add to local.conf:

    PACKAGECONFIG_append_pn-gstreamer1.0-plugins-good = "vpx wavpack"

Note that after enabling a plugin this way, it must be ensured that recipes for the plugin's dependencies
are available. In the example above, recipes for vpx and wavpack must exist. This typically means that
additional OE layers must be used (often meta-oe or meta-multimedia).

This is also how Orc support is enabled internally. Since version 1.2.2, Orc 0.4.18 is included in this layer,
and enabled by default. (Orc has been added to OE core, but not for dora; it is contained in meta-openembedded
dora branch, but for convenience - using GStreamer without Orc is rarely a good idea - the recipe is also
included in this layer.)

Below is a list of all configuration values for enabling additional plugins and features in the packages.
git versions of the packages might have additional configuration values. These values that exist (currently)
only in the git version are marked with "(git)".

* gstreamer1.0-plugins-base
    * `pango` : Pango plugins
* gstreamer1.0-plugins-good
    * `jack` : JACK audio system plugins
    * `vpx` : plugins for en- and decoding VP8 video streams, using Google's libvpx
    * `wavpack` : WavPack plugins
* gstreamer1.0-plugins-bad
    * `assrender` : ASS/SSA subtitle renderer plugins
    * `faac` : AAC encoding plugins using the FAAC library
    * `faad` : AAC decoding plugins using the FAAD library
    * `libmms` : Microsoft Multimedia Stream plugins
    * `modplug` : Decoder plugins for module files (MOD/S3M/XM/IT/..) using the ModPlug library
    * `mpg123` : MPEG-1 layer 1/2/3 audio decoder plugin using the mpg123 library
    * `opus` : Opus audio decoder plugin
    * `flite` : Flite speech synthesizer plugins
    * `opencv` : OpenCV image processing plugins
    * `openal` : OpenAL audio plugins
    * `fluidsynth` : FluidSynth plugins
    * `schroedinger` : Dirac video codec plugins using the schroedinger library
    * `rsvg` : librsvg plugins
    * `webp` : WebP plugins
* gstreamer1.0-plugins-ugly
    * `cdio` : Compact Disc audio plugins using libcdio
    * `dvdread` : DVD source plugins using libdvdread
    * `x264` : h.264/AVC encoder plugin using libx264
* gstreamer1.0-libav
    * `libav` : builds the package using the system's libav instead of the included one (*not recommended* unless you really know what you are doing!)
    * `lgpl` : build the package in LGPL mode (disables GPL elements)
    * `yasm` : enable support for the yasm assembler (recommended for performance)


OpenMAX IL support
------------------

The gstreamer1.0-omx package adds support for OpenMAX IL. By default, the
recipe is configured to use the bellagio OpenMAX IL implementation that ships
with OE-Core. BSP layers are encouraged to add .bbappend files which set the
`GSTREAMER_1_0_OMX_TARGET` variable to make gstreamer1.0-omx use the device
specific OpenMAX IL support. Currently, these device specific targets are
supported:

* `rpi` : Raspberry Pi OpenMAX IL implementation

If the value of `GSTREAMER_1_0_OMX_TARGET` is changed by a .bbappend file to
a device specific value, the recipe automatically sets the `PACKAGE_ARCH` of
gstreamer1.0-omx to `MACHINE_ARCH`.
