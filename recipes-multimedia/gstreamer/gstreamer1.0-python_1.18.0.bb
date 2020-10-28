SUMMARY = "Python bindings for GStreamer 1.0"
HOMEPAGE = "http://cgit.freedesktop.org/gstreamer/gst-python/"
SECTION = "multimedia"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=c34deae4e395ca07e725ab0076a5f740"

SRC_URI = "git://gitlab.freedesktop.org/gstreamer/gst-python.git;protocol=https;nobranch=1;tag=${PV}"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+\.(\d*[02468])+(\.\d+)+)"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base python3-pygobject"
RDEPENDS_${PN} += "gstreamer1.0 gstreamer1.0-plugins-base python3-pygobject"

S = "${WORKDIR}/git"

# gobject-introspection is mandatory and cannot be configured
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"
UNKNOWN_CONFIGURE_WHITELIST_append = " introspection"

inherit meson pkgconfig distutils3-base gobject-introspection features_check
