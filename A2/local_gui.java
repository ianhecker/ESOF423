public void gui_init_local()
    {
        setLayout(new BorderLayout());
        currDirPanel.setFloatable(false);
        buttonPanel.setFloatable(false);

        FlowLayout f = new FlowLayout(FlowLayout.RIGHT);
        f.setHgap(1);
        f.setVgap(2);

        buttonPanel.setLayout(f);
        buttonPanel.setMargin(new Insets(0, 0, 0, 0));

        runFile.addActionListener(this);
        viewFile.addActionListener(this);
        props.addActionListener(this);
        popupMenu.add(runFile);
        popupMenu.add(viewFile);
        popupMenu.add(props);

        deleteButton = new HImageButton(Settings.deleteImage, deleteString,
                                        "Delete selected", this);
        deleteButton.setToolTipText("Delete selected");

        mkdirButton = new HImageButton(Settings.mkdirImage, mkdirString,
                                       "Create a new directory", this);
        mkdirButton.setToolTipText("Create directory");

        refreshButton = new HImageButton(Settings.refreshImage, refreshString,
                                         "Refresh current directory", this);
        refreshButton.setToolTipText("Refresh directory");    
		refreshButton.setRolloverIcon(new ImageIcon(HImage.getImage(this, Settings.refreshImage2)));
		refreshButton.setRolloverEnabled(true);

        cdButton = new HImageButton(Settings.cdImage, cdString,
                                    "Change directory", this);
        cdButton.setToolTipText("Change directory");

        uploadButton = new HImageButton(Settings.uploadImage, uploadString,
                                        "Upload selected", this);
        uploadButton.setToolTipText("Upload selected");               
        //uploadButton.setBackground(new Color(192,192,192));

        zipButton = new HImageButton(Settings.zipFileImage, zipString,
                                     "Add selected to new zip file", this);
        zipButton.setToolTipText("Create zip");

        cpButton = new HImageButton(Settings.copyImage, cpString,
                                    "Copy selected files to another local dir",
                                    this);
        cpButton.setToolTipText("Local copy selected");

        rnButton = new HImageButton(Settings.textFileImage, rnString,
                                    "Rename selected file or directory", this);
        rnButton.setToolTipText("Rename selected");

        cdUpButton = new HImageButton(Settings.cdUpImage, cdUpString,
                                      "Go to Parent Directory", this);
        cdUpButton.setToolTipText("Go to Parent Directory");

        label.setText("Filesystem: " + StringUtils.cutPath(path));
        label.setSize(getSize().width - 10, 24);
        currDirPanel.add(label);
        currDirPanel.setSize(getSize().width - 10, 32);
        label.setSize(getSize().width - 20, 24);

        p.setLayout(new BorderLayout());
        p.add("North", currDirPanel);

        buttonPanel.add(sorter);

        buttonPanel.add(new JLabel("  "));

        buttonPanel.add(refreshButton);
        buttonPanel.add(new JLabel("  "));

        buttonPanel.add(cpButton);
        buttonPanel.add(rnButton);
        buttonPanel.add(mkdirButton);

        buttonPanel.add(cdButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cdUpButton);
        buttonPanel.add(new JLabel("  "));

        buttonPanel.add(zipButton);
        buttonPanel.add(new JLabel("              "));

        buttonPanel.setVisible(true);

        buttonPanel.setSize(getSize().width - 10, 32);

        sorter.addActionListener(this);
   
        p.add("South", buttonPanel);                      
        
        JPanel second = new JPanel();
        second.setLayout(new BorderLayout());
        second.add("Center", p);
        uploadButton.setMinimumSize(new Dimension(50, 50));
        uploadButton.setPreferredSize(new Dimension(50, 50));
        uploadButton.setMaximumSize(new Dimension(50, 50));
        second.add("East",uploadButton);
        
        add("North", second);
               

        setDirList(true);
        jlm = new DefaultListModel();
        jl = new JList(jlm);
        jl.setCellRenderer(new DirCellRenderer());
        jl.setVisibleRowCount(Settings.visibleFileRows);

        // add this becaus we need to fetch only doubleclicks
        MouseListener mouseListener = new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(JFtp.uiBlocked)
                {
                    return;
                }

                if(e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e))
                {
                    int index = jl.getSelectedIndex() - 1;

                    if(index < -1)
                    {
                        return;
                    }

                    String tgt = (String) jl.getSelectedValue().toString();

                    if(index < 0)
                    {
                    }
                    else if((dirEntry == null) || (dirEntry.length < index) ||
                                (dirEntry[index] == null))
                    {
                        return;
                    }
                    else
                    {
                        currentPopup = dirEntry[index];
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }

            public void mouseClicked(MouseEvent e)
            {
                if(JFtp.uiBlocked)
                {
                    return;
                }

        		TableUtils.copyTableSelectionsToJList(jl, table);

                //System.out.println("DirEntryListener::");
                if(e.getClickCount() == 2)
                {
                    //System.out.println("2xList selection: "+jl.getSelectedValue().toString());
                    int index = jl.getSelectedIndex() - 1;

                    // mousewheel bugfix, ui refresh bugfix
                    if(index < -1)
                    {
                        return;
                    }

                    String tgt = (String) jl.getSelectedValue().toString();

                    //System.out.println("List selection: "+index);
                    if(index < 0)
                    {
                    	doChdir(path+tgt);
                    }
                    else if((dirEntry == null) || (dirEntry.length < index) ||
                                (dirEntry[index] == null))
                    {
                        return;
                    }
                    else if(dirEntry[index].isDirectory())
                    {
                        doChdir(path+tgt);
                    }
                    else
                    {
                        showContentWindow(path + dirEntry[index].toString(),
                                          dirEntry[index]);

                        //blockedTransfer(index);
                    }
                }
            }
        };


        jsp = new JScrollPane(table);
        table.getSelectionModel().addListSelectionListener(this);
        table.addMouseListener(mouseListener);

        AdjustmentListener adjustmentListener = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                jsp.repaint();
                jsp.revalidate();
            }
        };

        jsp.getHorizontalScrollBar().addAdjustmentListener(adjustmentListener);
        jsp.getVerticalScrollBar().addAdjustmentListener(adjustmentListener);

        
        jsp.setSize(getSize().width - 20, getSize().height - 72);
        add("Center", jsp);
        jsp.setVisible(true);
        
        TableUtils.tryToEnableRowSorting(table);
        
        if(Settings.IS_JAVA_1_6) {
        	//sorter.setVisible(false);
        	buttonPanel.remove(sorter);
        }
        
        setVisible(true);
    }