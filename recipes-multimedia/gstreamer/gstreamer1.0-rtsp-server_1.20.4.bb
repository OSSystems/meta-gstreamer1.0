SUMMARY = "A library on top of GStreamer for building an RTSP server"
HOMEPAGE = "http://cgit.freedesktop.org/gstreamer/gst-rtsp-server/"
SECTION = "multimedia"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=69333daa044cb77e486cc36129f7a770"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

PNREAL = "gst-rtsp-server"

SRC_URI = "https://gstreamer.freedesktop.org/src/${PNREAL}/${PNREAL}-${PV}.tar.xz"

SRC_URI[sha256sum] = "88d9ef634e59aeb8cc183ad5ae444557c5a88dd49d833b9072bc6e1fae6a3d7d"

S = "${WORKDIR}/${PNREAL}-${PV}"

inherit meson pkgconfig upstream-version-is-even gobject-introspection

EXTRA_OEMESON += " \
    -Ddoc=disabled \
    -Dexamples=disabled \
    -Dtests=disabled \
"

GIR_MESON_ENABLE_FLAG = "enabled"
GIR_MESON_DISABLE_FLAG = "disabled"

# Starting with 1.8.0 gst-rtsp-server includes dependency-less plugins as well
require gstreamer1.0-plugins-packaging.inc

CVE_PRODUCT += "gst-rtsp-server"
