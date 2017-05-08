SUMMARY = "VA-API support to GStreamer"
DESCRIPTION = "gstreamer-vaapi consists of a collection of VA-API \
based plugins for GStreamer and helper libraries: `vaapidecode', \
`vaapiconvert', and `vaapisink'."

REALPN = "gstreamer-vaapi"
FILESPATH = "${@base_set_filespath(["${FILE_DIRNAME}/${REALPN}", "${FILE_DIRNAME}/${REALPN}"], d)}"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "libva"

SRC_URI = "https://gstreamer.freedesktop.org/src/${REALPN}/${REALPN}-${PV}.tar.xz \
          "

S = "${WORKDIR}/${REALPN}-${PV}"

inherit autotools pkgconfig gtk-doc distro_features_check upstream-version-is-even

REQUIRED_DISTRO_FEATURES ?= "opengl"

PACKAGES =+ "${PN}-tests"

# opengl packageconfig factored out to make it easy for distros
# and BSP layers to pick either glx, egl, or no GL
PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'egl', '', d)}"

PACKAGECONFIG ??= "drm \
                   ${PACKAGECONFIG_GL} \
                   ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "wayland", "", d)} \
                   ${@bb.utils.contains("DISTRO_FEATURES", "x11", "x11", "", d)}"

PACKAGECONFIG[drm] = "--enable-drm,--disable-drm,udev libdrm"
PACKAGECONFIG[egl] = "--enable-egl,--disable-egl,virtual/egl"
PACKAGECONFIG[glx] = "--enable-glx,--disable-glx,virtual/libgl"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,wayland"
PACKAGECONFIG[x11] = "--enable-x11,--disable-x11,virtual/libx11 libxrandr libxrender"

FILES_${PN} += "${libdir}/gstreamer-*/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-*/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-*/*.la ${libdir}/gstreamer-*/*.a"
FILES_${PN}-tests = "${bindir}/*"
