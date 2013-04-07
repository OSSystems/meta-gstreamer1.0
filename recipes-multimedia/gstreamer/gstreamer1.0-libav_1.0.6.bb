DESCRIPTION = "Libav-based GStreamer 1.x plugin"
SECTION = "multimedia"
LICENSE = "GPLv2+ & LGPLv2+ & ( (GPLv2+ & LGPLv2.1+) | (GPLv3+ & LGPLv3+) )"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
                    file://ext/libav/gstav.h;beginline=1;endline=18;md5=ff65467b0c53cdfa98d0684c1bc240a9 \
                    file://gst-libs/ext/libav/LICENSE;md5=abc3b8cb02856aa7823bbbd162d16232 \
                    file://gst-libs/ext/libav/COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://gst-libs/ext/libav/COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://gst-libs/ext/libav/COPYING.LGPLv2.1;md5=e344c8fa836c3a41c4cbd79d7bd3a379 \
                    file://gst-libs/ext/libav/COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"
LICENSE_FLAGS = "commercial"
HOMEPAGE = "http://www.gstreamer.net/"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base zlib bzip2"

inherit autotools pkgconfig

SRC_URI = " \
	http://gstreamer.freedesktop.org/src/gst-libav/gst-libav-${PV}.tar.xz \
	file://0001-Disable-yasm-for-libav-when-disable-yasm.patch \
	file://libav_e500mc.patch \
	"

SRC_URI[md5sum] = "07e5399c44346a95aafc1d4533125774"
SRC_URI[sha256sum] = "8ab222a52bf7482e913f2c9a4f490cda8f8ed1acfbc429f27451b0558b08044d"

S = "${WORKDIR}/gst-libav-${PV}"

PR = "r1"

GSTREAMER_1_0_DEBUG ?= "--disable-debug"
GSTREAMER_1_0_LIBAV_YASM ?= "--disable-yasm"

LIBAV_EXTRA_CONFIGURE = "--with-libav-extra-configure"
LIBAV_EXTRA_CONFIGURE_COMMON_ARG = "--target-os=linux \
  --cc='${CC}' --as='${CC}' --ld='${CC}' --nm='${NM}' --ar='${AR}' \
  --ranlib='${RANLIB}' \
  ${GSTREAMER_1_0_DEBUG}"
LIBAV_EXTRA_CONFIGURE_COMMON = \
'${LIBAV_EXTRA_CONFIGURE}="${LIBAV_EXTRA_CONFIGURE_COMMON_ARG}"'

EXTRA_OECONF = "${LIBAV_EXTRA_CONFIGURE_COMMON}"

# yasm not found, use --disable-yasm for a crippled build for libav
EXTRA_OECONF_append_x86-64 = " ${GSTREAMER_1_0_LIBAV_YASM} "
EXTRA_OECONF_append_x86 = " ${GSTREAMER_1_0_LIBAV_YASM} "

FILES_${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-1.0/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-1.0/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"
