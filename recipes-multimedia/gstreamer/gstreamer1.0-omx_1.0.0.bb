DESCRIPTION = "OpenMAX IL plugins for GStreamer"
SECTION = "multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f"
LICENSE_FLAGS = "commercial"
HOMEPAGE = "http://www.gstreamer.net/"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"
RDEPENDS_${PN} = "libomxil"

inherit autotools pkgconfig gettext

acpaths = "-I ${S}/common/m4 -I ${S}/m4"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz \
           "

SRC_URI[md5sum] = "bb34b5742223267298bcffc209104a92"
SRC_URI[sha256sum] = "7a1d8d28d70dacc6bd3c7ee7d7e40df6d5a1d38d7c256d5c9c5c8ef15c005014"

S = "${WORKDIR}/gst-omx-${PV}"

PR = "r1"

GSTREAMER_1_0_OMX_TARGET ?= "bellagio"

EXTRA_OECONF += "--disable-valgrind --with-omx-target=${GSTREAMER_1_0_OMX_TARGET}"

python __anonymous () {
    omx_target = d.getVar("GSTREAMER_1_0_OMX_TARGET", True)
    if omx_target in ['generic', 'bellagio']:
        srcdir = d.getVar("S", True)
        # Bellagio headers are incomplete (they are missing the OMX_VERSION_MAJOR,#
        # OMX_VERSION_MINOR, OMX_VERSION_REVISION, and OMX_VERSION_STEP macros);
        # appending a directory path to gst-omx' internal OpenMAX IL headers fixes this
        d.appendVar("CFLAGS", " -I%s/omx/openmax" % srcdir)
    elif omx_target == "rpi":
        # Dedicated Raspberry Pi OpenMAX IL support makes this package machine specific
        d.setVar("PACKAGE_ARCH", d.getVar("MACHINE_ARCH", True))
}

FILES_${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-1.0/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-1.0/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"
