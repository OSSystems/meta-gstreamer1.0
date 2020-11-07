SUMMARY = "VA-API support to GStreamer"
DESCRIPTION = "gstreamer-vaapi consists of a collection of VA-API \
based plugins for GStreamer and helper libraries: `vaapidecode', \
`vaapiconvert', and `vaapisink'."

REALPN = "gstreamer-vaapi"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "https://gstreamer.freedesktop.org/src/${REALPN}/${REALPN}-${PV}.tar.xz \
           file://0001-gst-vaapi-Makefile.am-Add-EGL_CFLAGS-to-libgstvaapi-.patch \
           file://0001-vaapsink-downgrade-to-marginal.patch \
           "

SRC_URI[md5sum] = "8c9b5a4d20afc04bc5e1536e81511f27"
SRC_URI[sha256sum] = "77200b3c183fe97cd987deb5544e615873cff5e98ec87573583771e5f1fb9ebe"

S = "${WORKDIR}/${REALPN}-${PV}"
DEPENDS = "libva gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad"

inherit autotools pkgconfig gtk-doc distro_features_check upstream-version-is-even

REQUIRED_DISTRO_FEATURES ?= "opengl"

PACKAGES =+ "${PN}-tests"

# OpenGL packageconfig factored out to make it easy for distros
# and BSP layers to pick either glx, egl, or no GL. By default,
# try detecting X11 first, and if found (with OpenGL), use GLX,
# otherwise try to check if EGL can be used.
PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'glx', \
                        bb.utils.contains('DISTRO_FEATURES',     'opengl', 'egl', \
                                                                       '', d), d)}"

PACKAGECONFIG ??= "drm \
                   ${PACKAGECONFIG_GL} \
                   ${@bb.utils.filter('DISTRO_FEATURES', 'wayland x11', d)}"

PACKAGECONFIG[drm] = "--enable-drm,--disable-drm,udev libdrm"
PACKAGECONFIG[egl] = "--enable-egl,--disable-egl,virtual/egl"
PACKAGECONFIG[glx] = "--enable-glx,--disable-glx,virtual/libgl"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,wayland-native wayland wayland-protocols"
PACKAGECONFIG[x11] = "--enable-x11,--disable-x11,virtual/libx11 libxrandr libxrender"

FILES_${PN} += "${libdir}/gstreamer-*/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-*/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-*/*.la ${libdir}/gstreamer-*/*.a"
FILES_${PN}-tests = "${bindir}/*"
